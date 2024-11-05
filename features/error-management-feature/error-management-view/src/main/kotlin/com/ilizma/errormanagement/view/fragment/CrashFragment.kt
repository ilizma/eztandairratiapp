package com.ilizma.errormanagement.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ilizma.errormanagement.view.R
import com.ilizma.errormanagement.view.bind.CrashFragmentBinder
import com.ilizma.errormanagement.view.databinding.CrashFragmentBinding
import com.ilizma.view.binding.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import kotlin.getValue

class CrashFragment : ScopeFragment(R.layout.crash_fragment) {

    private val binder: CrashFragmentBinder by inject()

    private val binding by viewBinding(CrashFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.bind(binding)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.close()
    }

}