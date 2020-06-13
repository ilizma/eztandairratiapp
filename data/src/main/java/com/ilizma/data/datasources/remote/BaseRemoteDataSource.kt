package com.ilizma.data.datasources.remote

import android.content.res.Resources
import androidx.annotation.VisibleForTesting
import com.ilizma.data.BuildConfig
import com.ilizma.data.R
import com.ilizma.data.entity.base.ErrorResponse
import com.ilizma.domain.entity.base.Failure
import com.ilizma.domain.entity.base.ResponseObject
import com.ilizma.domain.entity.base.Success
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Lazy
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava3.Result
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

private const val TIMEOUT_TIME = 30L
private const val RETRY_TIMES = 1

abstract class BaseRemoteDataSource {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    abstract var resources: Lazy<Resources>

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    abstract var moshi: Lazy<Moshi>

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun <RO : ResponseObject<DO>, DO : Any> modifySingle(single: Single<Result<RO>>): Single<DO> =
        single
            .flatMap { data ->
                Single.create<DO> { emitter ->
                    data.response()?.let { response ->
                        val body: RO? = response.body()
                        val errorBody = response.errorBody()

                        if (emitter.isDisposed.not()) {
                            when {
                                response.isSuccessful && body != null ->
                                    emitter.onSuccess(getDomainObject(body))
                                response.isSuccessful && body == null ->
                                    emitter.onSuccess(getDomainObjectNoResponse())
                                response.isSuccessful.not() ->
                                    emitter.tryOnError(getFailureErrorWithErrorResponse(errorBody))
                                else -> emitter.tryOnError(getFailureUnknownError())
                            }
                        }

                    } ?: run {
                        Timber.e("BaseRemoteDataSource: modifySingle ${data.error().toString()}")
                        if (emitter.isDisposed.not()) emitter.tryOnError(getFailureError(data.error()))
                    }

                }
            }
            .onErrorResumeNext {
                Single.error(getFailureUnknownError())
            }
            .timeout(TIMEOUT_TIME, TimeUnit.SECONDS, Single.create { emitter ->
                if (emitter.isDisposed.not()) emitter.tryOnError(getFailureTimeout())
            })
            .retry { count, throwable ->
                count <= RETRY_TIMES && (throwable is Failure.Timeout || throwable is Failure.NoInternet)
            }

    @Suppress("UNCHECKED_CAST")
    private fun <RO : ResponseObject<DO>, DO : Any> getDomainObject(body: RO): DO =
        (body as ResponseObject<Any>).toDomain() as DO

    @Suppress("UNCHECKED_CAST")
    private fun <DO : Any> getDomainObjectNoResponse(): DO =
        Success() as DO

    private fun getFailureErrorWithErrorResponse(errorBody: ResponseBody?): Failure.FailureWithMessage {
        val errorResponse = parseErrorResponse(errorBody)
        val message = if (errorResponse.message.isNotEmpty()) {
            errorResponse.message
        } else {
            resources.get().getString(R.string.unknown_error)
        }
        return Failure.Error(message)
    }

    private fun getFailureError(throwable: Throwable?): Failure {
        Timber.e("getFailureError ${throwable?.message}")

        return when (throwable) {
            is UnknownHostException ->
                Failure.NoInternet(resources.get().getString(R.string.no_internet))
            else -> Failure.Error(
                if (BuildConfig.DEBUG) {
                    throwable?.message ?: resources.get().getString(R.string.unknown_error)
                } else {
                    resources.get().getString(R.string.unknown_error)
                }
            )
        }
    }

    private fun getFailureUnknownError(): Failure.Error =
        Failure.Error(resources.get().getString(R.string.unknown_error))

    private fun getFailureTimeout(): Failure.Timeout =
        Failure.Timeout(resources.get().getString(R.string.timeout_message))

    private fun parseErrorResponse(responseBody: ResponseBody?): ErrorResponse {
        val type: ParameterizedType = Types.newParameterizedType(ErrorResponse::class.java)
        val adapter: JsonAdapter<ErrorResponse> = moshi.get().adapter(type)
        return try {
            responseBody?.let { adapter.fromJson(it.string()) } ?: getUnknownErrorResponse()
        } catch (exception: Exception) {
            Timber.e("parseErrorResponse ${exception.message}")
            getUnknownErrorResponse()
        }
    }

    private fun getUnknownErrorResponse() =
        ErrorResponse(resources.get().getString(R.string.unknown_error))

}