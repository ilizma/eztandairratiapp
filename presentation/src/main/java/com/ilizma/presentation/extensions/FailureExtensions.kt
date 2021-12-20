package com.ilizma.presentation.extensions

import com.google.android.material.snackbar.Snackbar
import com.ilizma.domain.entity.base.Failure
import com.ilizma.presentation.R
import com.ilizma.presentation.ui.base.BaseActivity
import com.ilizma.presentation.ui.base.BaseFragment

private const val UNKNOWN = "Unknown"

fun Throwable.getFailureMessage(
): String = when (this) {
    is Failure.FailureWithMessage -> msg
    else -> UNKNOWN
}

fun BaseFragment.handleNormalFailure(
    failure: Failure,
) {
    handleNormalLengthFailure(failure)
}

fun BaseActivity.handleNormalLengthFailure(
    failure: Failure,
    length: Int = Snackbar.LENGTH_INDEFINITE,
) {
    when (failure) {
        is Failure.FailureWithMessage -> showSnackbar(
            failure.msg,
            getString(R.string.retry),
            length,
        ) { failure.retryAction() }
        else -> showSnackbarWithRes(
            R.string.unknown_error,
            R.string.retry,
            length,
        ) { failure.retryAction() }
    }
}

fun BaseFragment.handleNormalLengthFailure(
    failure: Failure,
    length: Int = Snackbar.LENGTH_INDEFINITE,
) {
    when (failure) {
        is Failure.FailureWithMessage -> showSnackbar(
            failure.msg,
            getString(R.string.retry),
            length,
        ) { failure.retryAction() }
        else -> showSnackbarWithRes(
            R.string.unknown_error,
            R.string.retry,
            length,
        ) { failure.retryAction() }
    }
}