package com.ilizma.player.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ilizma.player.view.R
import com.ilizma.player.view.bind.RadioScreenBinder
import com.ilizma.player.view.databinding.RadioScreenFragmentBinding
import com.ilizma.player.view.router.RadioScreenRouter
import com.ilizma.view.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RadioScreenFragment : Fragment(R.layout.radio_screen_fragment) {

    @Inject
    internal lateinit var binder: RadioScreenBinder

    @Inject
    internal lateinit var router: RadioScreenRouter

    private val binding by viewBinding(RadioScreenFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        router.init()
    }

    private fun setupView() {
        binder.bind(binding)
    }

}