package com.ilizma.domain.extensions

import com.ilizma.domain.entity.base.Failure
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber

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

fun <T : Any> Single<T>.testAwait(): TestObserver<T> = this.test().apply {
    this.awaitTerminalEvent()
}

fun Completable.testAwait(): TestObserver<Void> = this.test().apply {
    this.awaitTerminalEvent()
}

private fun getFailureError() = Failure.Error("Error")

fun <T> getSingleSuccess(value: T): Single<T> = Single.just(value)

fun <T> getSingleError(value: Throwable = getFailureError()): Single<T> = Single.error(value)

fun <T> getSingleListSuccess(value: List<T>): Single<List<T>> = Single.just(value)

fun <T> getSingleListError(): Single<List<T>> = Single.error(getFailureError())

fun getCompletableComplete(): Completable = Completable.complete()

fun getCompletableError(value: Throwable = getFailureError()): Completable =
    Completable.error(value)