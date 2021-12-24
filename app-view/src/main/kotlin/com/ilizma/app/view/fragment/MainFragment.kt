package com.ilizma.app.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ilizma.app.view.R
import com.ilizma.app.view.bind.MainFragmentBinder
import com.ilizma.app.view.databinding.MainFragmentBinding
import com.ilizma.view.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    @Inject
    internal lateinit var binder: MainFragmentBinder

    private val binding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binder.bind(binding)
    }

}
