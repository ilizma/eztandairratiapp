package com.ilizma.data.extensions

import com.ilizma.domain.entity.base.Failure
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

fun <T : Any> TestSubscriber<T>.assertGeneralsSuccess(asserts: (T) -> Boolean = { true }) {
    assertComplete()
    assertValueCount(1)
    assertNoErrors()
    assertValue {
        asserts(it)
    }
}

fun <T : Any> TestObserver<T>.assertGeneralsSuccess(asserts: (T) -> Boolean = { true }) {
    assertComplete()
    assertValueCount(1)
    assertNoErrors()
    assertValue {
        asserts(it)
    }
}

fun <T : Any> TestObserver<T>.assertGeneralsObservableSuccess(
    count: Int = 1,
    asserts: (T) -> Boolean = { true }
) {
    assertValueCount(count)
    assertNoErrors()
    assertValue {
        asserts(it)
    }
}

fun <T : Any> TestObserver<T>.assertGeneralsCompletableSuccess() {
    assertComplete()
    assertNoErrors()
}

fun <T : Any> TestObserver<T>.assertGeneralsError(asserts: (Throwable) -> Boolean = { true }) {
    assertValueCount(0)
    assertError {
        asserts(it)
    }
}

const val DEFAULT_CODE_ERROR = 400
const val DEFAULT_CODE_SUCCESS = 200

private fun getFailureError() = Failure.Error("Error")

fun createResponseBody(): ResponseBody = ResponseBody.create(null, byteArrayOf())

fun createResponseBodyWithErrorResponse(reason: String = ""): ResponseBody {
    val content = "{\"code\":400, \"message\":\"message\", \"reason\":\"$reason\"}"
    return content.toResponseBody("application/json".toMediaTypeOrNull())
}

fun <T> getSingleResultSuccess(value: T): Single<Result<T>> =
    Single.just(Result.response(Response.success(value)))

fun <T : List<Any>> getSingleListResultSuccess(value: T): Single<Result<T>> =
    Single.just(Result.response(Response.success(value)))

fun <T> getSingleResultError(
    errorCode: Int = DEFAULT_CODE_ERROR,
    reason: String = ""
): Single<Result<T>> {
    val responseBody = if (reason.isEmpty()) {
        createResponseBody()
    } else {
        createResponseBodyWithErrorResponse(reason)
    }

    return Single.just(Result.response(Response.error(errorCode, responseBody)))
}

fun <T : List<Any>> getSingleListResultError(
    errorCode: Int = DEFAULT_CODE_ERROR,
    reason: String = ""
): Single<Result<T>> {
    val responseBody = if (reason.isEmpty()) {
        createResponseBody()
    } else {
        createResponseBodyWithErrorResponse(reason)
    }

    return Single.just(Result.response(Response.error(errorCode, responseBody)))
}

fun <T> getFlowableSuccess(value: T): Flowable<T> = Flowable.just(value)

fun <T> getFlowableError(value: Throwable = getFailureError()): Flowable<T> = Flowable.error(value)

fun <T> getSingleSuccess(value: T): Single<T> = Single.just(value)

fun <T> getSingleError(value: Throwable = getFailureError()): Single<T> = Single.error(value)

fun <T> getObservableSuccess(value: T): Observable<T> = Observable.just(value)

fun <T> getObservableError(): Observable<T> = Observable.error(getFailureError())

fun getCompletableComplete(): Completable = Completable.complete()

fun getCompletableError(value: Throwable = getFailureError()): Completable = Completable.error(value)