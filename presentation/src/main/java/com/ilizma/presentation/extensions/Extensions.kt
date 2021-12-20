package com.ilizma.presentation.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun <T1 : Any, T2 : Any, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    block: (T1, T2) -> R?,
): R? = if (p1 != null && p2 != null) block(p1, p2) else null

fun Context.callNumber(
    number: String,
) {
    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number")))
}

fun Context.openUrl(
    url: String
) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Context.hideKeyboard(
    view: View?
) {
    view?.let {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}