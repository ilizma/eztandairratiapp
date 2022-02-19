package com.ilizma.errormanagement.view.bind

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.view.isVisible
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.ilizma.errormanagement.view.imp.BuildConfig
import com.ilizma.errormanagement.view.databinding.CrashActivityBinding
import com.ilizma.errormanagement.view.imp.R
import com.ilizma.view.extensions.setOnReactiveClickListener
import com.ilizma.view.extensions.snackbar
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

private const val CLIPBOARD_LABEL = "EztandaCrashActivity log"

class CrashActivityBinderImp : CrashActivityBinder {

    private lateinit var binding: CrashActivityBinding
    private lateinit var activity: Activity

    private val stackTrace = AtomicReference<String>()

    override fun bind(
        binding: CrashActivityBinding,
        activity: Activity,
    ) {
        this.binding = binding
        this.activity = activity
        setupView()
        setUpListeners()
    }

    private fun setupView() {
        if (BuildConfig.DEBUG) binding.crashActivityBLogcat.isVisible = true

        CustomActivityOnCrash.getAllErrorDetailsFromIntent(binding.root.context, activity.intent)
            .let { stackTrace.set(it) }
    }

    private fun setUpListeners() {
        binding.crashActivityBRestart.setOnReactiveClickListener {
            CustomActivityOnCrash.getConfigFromIntent(activity.intent)
                ?.let { CustomActivityOnCrash.restartApplication(activity, it) }
        }

        binding.crashActivityBLogcat.setOnReactiveClickListener {
            binding.crashActivityTvLog.text = stackTrace.get()
            binding.crashActivityBClipboard.isVisible = true
        }

        binding.crashActivityBClipboard.setOnReactiveClickListener {
            val clipboard = binding.root.context
                .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(CLIPBOARD_LABEL, binding.crashActivityTvLog.text)
            clipboard.setPrimaryClip(clip)

            binding.root.snackbar(
                R.string.crash_activity_snackbar_successful_title,
                R.string.crash_activity_snackbar_successful_action,
            )
        }
    }

}