package com.ilizma.domain.entity.base

sealed class Failure(var retryAction: () -> Unit) : Throwable() {

    abstract class FailureWithMessage(open val msg: String) : Failure({})

    class Error(override val msg: String) : FailureWithMessage(msg)

    class Timeout(override val msg: String) : FailureWithMessage(msg)

    class NoInternet(override val msg: String) : FailureWithMessage(msg)

    object NotInDatabase : Failure({})

}