package com.ilizma.presentation.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.snackbar
import dagger.Lazy
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @setparam:LayoutRes
    abstract var activityLayout: Int

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
    }

    override fun onPause() {
        dismissSnackbar()
        super.onPause()
    }

    private fun dismissSnackbar() {
        snackbar?.dismiss()
    }

    fun showSnackbarWithRes(
        @StringRes title: Int,
        @StringRes action: Int,
        length: Int = Snackbar.LENGTH_INDEFINITE,
        actionResult: () -> Unit = {}
    ) {
        val container = findViewById<View?>(R.id.parentContainer)
        snackbar = container?.snackbar(title, action, length, actionResult = actionResult)
    }

    fun showSnackbar(
        title: String,
        action: String,
        length: Int = Snackbar.LENGTH_INDEFINITE,
        actionResult: () -> Unit = {}
    ) {
        val container = findViewById<View?>(R.id.parentContainer)
        snackbar = container?.snackbar(title, action, length, actionResult = actionResult)
    }

}