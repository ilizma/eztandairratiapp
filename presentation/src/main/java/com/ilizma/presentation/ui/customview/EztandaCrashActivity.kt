package com.ilizma.presentation.ui.customview

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.google.android.material.snackbar.Snackbar
import com.ilizma.presentation.BuildConfig
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.setOnReactiveClickListener
import com.ilizma.presentation.extensions.visible
import com.ilizma.presentation.ui.base.BaseActivity
import com.ilizma.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_crash.*
import java.util.concurrent.atomic.AtomicReference

private const val KEYWORD_TO_MATCH_IN_LOG = "at com.ilizma."

class EztandaCrashActivity : BaseActivity() {

    private val clipboardLabel = "EztandaCrashActivity log"

    override var activityLayout: Int = R.layout.activity_crash
    override var childFragment: BaseFragment? = null

    private val stackTrace = AtomicReference<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("ConstantConditionIf")
        if (BuildConfig.DEBUG) {
            logcatBtn.visible()
        }

        stackTrace.set(CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, intent))

        setUpListeners()
    }

    private fun setUpListeners() {
        restartBtn.setOnReactiveClickListener {
            CustomActivityOnCrash.getConfigFromIntent(intent)?.let { caocConfig ->
                CustomActivityOnCrash.restartApplication(this, caocConfig)
            }
        }

        logcatBtn.setOnReactiveClickListener {
            logText.text = stackTrace.get()

            clipboardBtn.visible()
        }

        clipboardBtn.setOnReactiveClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(clipboardLabel, logText.text)
            clipboard.setPrimaryClip(clip)

            showSnackbarWithRes(
                R.string.crash_activity_snackbar_successful_title,
                R.string.crash_activity_snackbar_successful_action,
                Snackbar.LENGTH_LONG
            )
        }
    }

    private fun extractClassNameFromException(log: String): String =
        extractFullPackageFromException(log)
            .substringAfterLast(".")

    private fun extractFullPackageFromException(log: String): String {
        val fullPackage = log
            .substringAfter(KEYWORD_TO_MATCH_IN_LOG)
            .substringBefore("$")

        return extractPackageCompanyName() + fullPackage
    }

    private fun extractPackageCompanyName(): String {
        return BuildConfig.LIBRARY_PACKAGE_NAME.substringBefore(".") + "."
    }

}