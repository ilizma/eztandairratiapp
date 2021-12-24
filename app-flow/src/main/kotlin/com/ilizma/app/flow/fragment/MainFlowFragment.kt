package com.ilizma.app.flow.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ilizma.app.flow.R
import com.ilizma.app.flow.bind.MainFlowFragmentBinder
import com.ilizma.app.flow.databinding.MainFlowFragmentBinding
import com.ilizma.view.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFlowFragment : Fragment(R.layout.main_flow_fragment) {

    @Inject
    internal lateinit var binder: MainFlowFragmentBinder

    private val binding by viewBinding(MainFlowFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binder.bind(binding)
    }
}