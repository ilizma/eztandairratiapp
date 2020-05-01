package com.ilizma.domain.entity.base

private const val UNKNOWN_ERROR_CODE = -1
private const val UNKNOWN_REASON = "UNKNOWN REASON"

sealed class Failure(var retryAction: () -> Unit) : Throwable() {

    abstract class FailureWithMessage(open val msg: String) : Failure({})

    class Error(val code: Int, override val msg: String, val reason: String) : FailureWithMessage(msg) {
        constructor(message: String) : this(
            UNKNOWN_ERROR_CODE, message,
            UNKNOWN_REASON
        )
        constructor(message: String, reason: String) : this(UNKNOWN_ERROR_CODE, message, reason)
    }

    class MultipleError(
        val code: Int = UNKNOWN_ERROR_CODE,
        override val msg: String,
        private val errors: Map<String, List<String>>?,
        val reason: String
    ) : FailureWithMessage(msg) {

        fun getErrorValue(key: String) = errors?.get(key)?.first() ?: msg

    }

    class Timeout(override val msg: String) : FailureWithMessage(msg)

    class NoInternet(override val msg: String) : FailureWithMessage(msg)

    object NotInDatabase : Failure({})

}