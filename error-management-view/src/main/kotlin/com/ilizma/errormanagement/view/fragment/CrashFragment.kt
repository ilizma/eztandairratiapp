package com.ilizma.errormanagement.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ilizma.errormanagement.view.R
import com.ilizma.errormanagement.view.bind.CrashFragmentBinder
import com.ilizma.errormanagement.view.databinding.CrashFragmentBinding
import com.ilizma.view.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CrashFragment : Fragment(R.layout.crash_fragment) {

    @Inject
    internal lateinit var binder: CrashFragmentBinder

    private val binding by viewBinding(CrashFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.bind(binding, requireActivity())
    }

}