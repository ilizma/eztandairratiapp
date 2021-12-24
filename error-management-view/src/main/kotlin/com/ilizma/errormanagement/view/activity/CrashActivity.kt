package com.ilizma.errormanagement.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ilizma.errormanagement.view.R
import com.ilizma.errormanagement.view.bind.CrashActivityBinder
import com.ilizma.errormanagement.view.databinding.CrashActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CrashActivity : AppCompatActivity(R.layout.crash_activity) {

    @Inject
    internal lateinit var binder: CrashActivityBinder

    private lateinit var binding: CrashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CrashActivityBinding.inflate(layoutInflater)
        setupView()
    }

    private fun setupView() {
        binder.bind(
            binding = binding,
            activity = this,
        )
    }

}