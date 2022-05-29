package com.ilizma.view.extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ilizma.resources.R
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

fun View.setOnReactiveClickListener(
    throttleInMillis: Long = 500,
    action: (() -> Unit)?
): Disposable = clicks()
    .throttleFirst(throttleInMillis, TimeUnit.MILLISECONDS)
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { action?.invoke() }

fun View.snackbar(
    title: String = "",
    action: String = "",
    length: Int = Snackbar.LENGTH_LONG,
    @ColorRes actionColor: Int = R.color.colorAccent,
    actionResult: () -> Unit = {},
): Snackbar {

    val snackbar = Snackbar.make(this, title, length)

    if (action.isNotEmpty()) {
        snackbar.setAction(action) { actionResult() }
        snackbar.setActionTextColor(ContextCompat.getColor(context, actionColor))
    }
    snackbar.show()

    return snackbar
}

fun View.snackbar(
    @StringRes titleRes: Int = 0,
    @StringRes actionRes: Int = 0,
    length: Int = Snackbar.LENGTH_LONG,
    @ColorRes actionColor: Int = R.color.colorAccent,
    actionResult: () -> Unit = {}
): Snackbar = snackbar(
    context.getString(titleRes),
    context.getString(actionRes),
    length,
    actionColor,
    actionResult
)