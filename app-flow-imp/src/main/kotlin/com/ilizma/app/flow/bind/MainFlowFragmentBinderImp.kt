package com.ilizma.app.flow.bind

import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ilizma.app.flow.databinding.MainFlowFragmentBinding
import com.ilizma.app.flow.R

class MainFlowFragmentBinderImp(
    private val fragmentManager: () -> FragmentManager
) : MainFlowFragmentBinder {

    private lateinit var binding: MainFlowFragmentBinding

    override fun bind(binding: MainFlowFragmentBinding) {
        this.binding = binding
        setupView()
    }

    private fun setupView() {
        val navHostFragment = fragmentManager()
            .findFragmentById(R.id.main_flow_fragment_f_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainFragmentBnvMenu.setupWithNavController(navController)
    }

}