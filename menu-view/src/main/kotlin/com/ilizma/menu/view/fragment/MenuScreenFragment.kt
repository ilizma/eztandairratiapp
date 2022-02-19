package com.ilizma.menu.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ilizma.menu.view.bind.MenuScreenFragmentBinder
import com.ilizma.menu.view.R
import com.ilizma.menu.view.databinding.MenuScreenFragmentBinding
import com.ilizma.menu.view.router.MenuScreenRouter
import com.ilizma.view.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuScreenFragment : Fragment(R.layout.menu_screen_fragment) {

    @Inject
    internal lateinit var binder: MenuScreenFragmentBinder

    @Inject
    internal lateinit var router: MenuScreenRouter

    private val binding by viewBinding(MenuScreenFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        router.init()
    }

    private fun setupView() {
        binder.bind(binding)
    }

}
