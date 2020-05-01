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
import io.reactivex.Single
import okhttp3.ResponseBody
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

open class BaseRemoteDataSource {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    @Inject
    lateinit var resources: Lazy<Resources>

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    @Inject
    lateinit var moshi: Lazy<Moshi>

    private val timeout = 30L
    private val retry = 1

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun modifySingleSuccess(
        single: Single<retrofit2.adapter.rxjava2.Result<ResponseBody>>,
        timeoutTime: Long = timeout,
        retryTimes: Int = retry,
        codeErrorHandler: ((Int) -> Failure)? = null,
        reasonErrorHandler: ((Failure) -> Failure)? = null
    ): Single<Success> =
        single
            .onErrorResumeNext {
                Single.error(getFailureUnknownError())
            }
            .flatMap { data ->
                Single.create<Success> { emitter ->

                    val checkCodeError = codeErrorHandler != null
                    val checkReasonError = reasonErrorHandler != null

                    if (data.response() == null) Timber.e(
                        "BaseRemoteDataSource: modifySingleSuccess ${data.error().toString()}"
                    )

                    data.response()?.let { response ->
                        val code = response.code()
                        val errorBody = response.errorBody()

                        if (emitter.isDisposed.not()) {
                            when {
                                response.isSuccessful -> emitter.onSuccess(
                                    getDomainObjectNoResponse(
                                        code
                                    )
                                )
                                checkCodeError -> emitter.tryOnError(codeErrorHandler!!.invoke(code))
                                response.isSuccessful.not() -> {
                                    val failure = getFailureErrorWithErrorResponse(code, errorBody)
                                    if (checkReasonError) {
                                        emitter.tryOnError(reasonErrorHandler!!.invoke(failure))
                                    } else {
                                        emitter.tryOnError(failure)
                                    }
                                }
                                else -> emitter.tryOnError(getFailureUnknownError())
                            }
                        }

                    } ?: run {
                        if (emitter.isDisposed.not()) {
                            emitter.tryOnError(getFailureError(data.error()))
                        }
                    }

                }
            }.timeout(timeoutTime, TimeUnit.SECONDS, Single.create<Success> { emitter ->
                if (emitter.isDisposed.not()) {
                    emitter.tryOnError(getFailureTimeout())
                }
            }).retry { count, throwable ->
                count <= retryTimes && (throwable is Failure.Timeout || throwable is Failure.NoInternet)
            }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun <RO : ResponseObject<DO>, DO : Any> modifySingle(
        single: Single<retrofit2.adapter.rxjava2.Result<RO>>,
        timeoutTime: Long = timeout,
        retryTimes: Int = retry,
        codeErrorHandler: ((Int) -> Failure)? = null,
        reasonErrorHandler: ((Failure) -> Failure)? = null
    ): Single<DO> =
        single
            .onErrorResumeNext {
                Single.error(getFailureUnknownError())
            }
            .flatMap { data ->
                Single.create<DO> { emitter ->

                    val checkCodeError = codeErrorHandler != null
                    val checkReasonError = reasonErrorHandler != null

                    if (data.response() == null) Timber.e(
                        "BaseRemoteDataSource: modifySingle ${data.error().toString()}"
                    )

                    data.response()?.let { response ->
                        val body: RO? = response.body()
                        val code = response.code()
                        val errorBody = response.errorBody()

                        if (emitter.isDisposed.not()) {
                            when {
                                response.isSuccessful && body != null -> emitter.onSuccess(
                                    getDomainObject(body)
                                )
                                response.isSuccessful && body == null -> emitter.onSuccess(
                                    getDomainObjectNoResponse(code)
                                )
                                checkCodeError -> emitter.tryOnError(codeErrorHandler!!.invoke(code))
                                response.isSuccessful.not() -> {
                                    val failure = getFailureErrorWithErrorResponse(code, errorBody)
                                    if (checkReasonError) {
                                        emitter.tryOnError(reasonErrorHandler!!.invoke(failure))
                                    } else {
                                        emitter.tryOnError(failure)
                                    }
                                }
                                else -> emitter.tryOnError(getFailureUnknownError())
                            }
                        }

                    } ?: run {
                        if (emitter.isDisposed.not()) {
                            emitter.tryOnError(getFailureError(data.error()))
                        }
                    }

                }
            }.timeout(timeoutTime, TimeUnit.SECONDS, Single.create<DO> { emitter ->
                if (emitter.isDisposed.not()) {
                    emitter.tryOnError(getFailureTimeout())
                }
            }).retry { count, throwable ->
                count <= retryTimes && (throwable is Failure.Timeout || throwable is Failure.NoInternet)
            }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun <RO : ResponseObject<DO>, DO : Any?> modifySingleList(
        single: Single<retrofit2.adapter.rxjava2.Result<List<RO>>>,
        timeoutTime: Long = timeout,
        retryTimes: Int = retry,
        codeErrorHandler: ((Int) -> Failure)? = null,
        reasonErrorHandler: ((Failure) -> Failure)? = null
    ): Single<List<DO>> =
        single
            .onErrorResumeNext {
                Single.error(getFailureUnknownError())
            }
            .flatMap { data ->
                Single.create<List<DO>> { emitter ->

                    val checkCodeError = codeErrorHandler != null
                    val checkReasonError = reasonErrorHandler != null

                    if (data.response() == null) Timber.e(
                        "BaseRemoteDataSource: modifySingleList ${data.error().toString()}"
                    )

                    data.response()?.let { response ->
                        val body: List<RO>? = response.body()
                        val code = response.code()
                        val errorBody = response.errorBody()

                        if (emitter.isDisposed.not()) {
                            when {
                                response.isSuccessful && body != null -> emitter.onSuccess(
                                    getDomainObjectList(body)
                                )
                                response.isSuccessful && body == null -> emitter.onSuccess(
                                    getDomainObjectNoResponse(code)
                                )
                                checkCodeError -> emitter.tryOnError(codeErrorHandler!!.invoke(code))
                                response.isSuccessful.not() -> {
                                    val failure = getFailureErrorWithErrorResponse(code, errorBody)
                                    if (checkReasonError) {
                                        emitter.tryOnError(reasonErrorHandler!!.invoke(failure))
                                    } else {
                                        emitter.tryOnError(failure)
                                    }
                                }
                                else -> emitter.tryOnError(getFailureUnknownError())
                            }
                        }

                    } ?: run {
                        if (emitter.isDisposed.not()) {
                            emitter.tryOnError(getFailureError(data.error()))
                        }
                    }

                }
            }.timeout(timeoutTime, TimeUnit.SECONDS, Single.create<List<DO>> { emitter ->
                if (emitter.isDisposed.not()) {
                    emitter.tryOnError(getFailureTimeout())
                }
            }).retry { count, throwable ->
                count <= retryTimes && (throwable is Failure.Timeout || throwable is Failure.NoInternet)
            }

    @Suppress("UNCHECKED_CAST")
    private fun <RO : ResponseObject<DO>, DO : Any> getDomainObject(body: RO): DO =
        (body as ResponseObject<Any>).toDomain() as DO

    @Suppress("UNCHECKED_CAST")
    private fun <RO : ResponseObject<DO>, DO : Any?> getDomainObjectList(body: List<RO>): List<DO> =
        (body as List<ResponseObject<Any>>).map { it.toDomain() } as List<DO>

    @Suppress("UNCHECKED_CAST")
    private fun <DO : Any> getDomainObjectNoResponse(code: Int): DO =
        Success(code) as DO

    private fun getFailureErrorWithErrorResponse(
        code: Int,
        errorBody: ResponseBody?
    ): Failure.FailureWithMessage {
        val errorResponse = parseErrorResponse(code, errorBody)
        val message =
            if (errorResponse.message.isNotEmpty()) errorResponse.message else resources.get()
                .getString(R.string.unknown_error)
        val reason =
            if (errorResponse.reason?.isNotEmpty() == true) errorResponse.reason else resources.get()
                .getString(R.string.unknown_reason)
        errorResponse.errors?.let { errorMap ->
            return Failure.MultipleError(code, message, errorMap, reason)
        }
        return Failure.Error(code, message, reason)
    }

    private fun getFailureError(throwable: Throwable?): Failure {
        Timber.e("getFailureError ${throwable?.message}")

        return when (throwable) {
            is UnknownHostException -> Failure.NoInternet(
                resources.get().getString(R.string.no_internet)
            )
            else -> {
                Failure.Error(
                    @Suppress("ConstantConditionIf")
                    if (BuildConfig.BUILD_TYPE == "release") {
                        resources.get().getString(R.string.unknown_error)
                    } else {
                        throwable?.message ?: resources.get().getString(R.string.unknown_error)
                    }
                )
            }
        }
    }

    protected fun getFailureUnknownError(): Failure.Error =
        Failure.Error(resources.get().getString(R.string.unknown_error))

    private fun getFailureTimeout(): Failure.Timeout =
        Failure.Timeout(resources.get().getString(R.string.timeout_message))

    private fun parseErrorResponse(code: Int, responseBody: ResponseBody?): ErrorResponse {
        val type: ParameterizedType = Types.newParameterizedType(ErrorResponse::class.java)
        val adapter: JsonAdapter<ErrorResponse> = moshi.get().adapter(type)
        return try {
            responseBody?.let { adapter.fromJson(it.string()) } ?: getDefaultErrorResponse(code)
        } catch (exception: Exception) {
            Timber.e("parseErrorResponse ${exception.message}")
            getDefaultErrorResponse(code)
        }
    }

    private fun getDefaultErrorResponse(code: Int) = ErrorResponse(
        code = code,
        message = resources.get().getString(R.string.unknown_error),
        errors = null,
        reason = resources.get().getString(R.string.unknown_reason)
    )

}