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

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @setparam:LayoutRes
    abstract var activityLayout: Int

    abstract var childFragment: BaseFragment?

    private var snackbar: Snackbar? = null

    protected val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
        childFragment?.let { pushChildStack(it) }
    }

    fun pushChildStack(fragment: BaseFragment) {
        if (isFinishing.not()) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentContainer, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }

    override fun onPause() {
        dismissSnackbar()
        super.onPause()
    }

    override fun onDestroy() {
        dispose()
        super.onDestroy()
    }

    protected fun showDialog(
        @StringRes title: Int,
        @StringRes msg: Int,
        @StringRes positiveButton: Int = android.R.string.ok,
        @StringRes negativeButton: Int = -1,
        positiveAction: () -> Unit = {},
        negativeAction: () -> Unit = {}
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setTitle(getString(title))
            .setMessage(getString(msg))
            .setPositiveButton(getString(positiveButton)) { _, _ ->
                positiveAction()
            }

        if (negativeButton != -1) {
            alertDialogBuilder.setNegativeButton(getString(negativeButton)) { _, _ ->
                negativeAction()
            }
        }

        alertDialogBuilder.create().show()
    }

    protected fun dismissSnackbar() {
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

    private fun dispose() {
        compositeDisposable.dispose()
    }

}