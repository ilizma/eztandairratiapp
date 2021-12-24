package com.ilizma.app.flow.bind

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ilizma.app.flow.databinding.MainFlowFragmentBinding
import com.ilizma.app.flow.imp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFlowFragmentBinderImp(
    private val activity: AppCompatActivity,
    private val navigation: Navigation,
    private val navigationUI: NavigationUI,
) : MainFlowFragmentBinder {

    private lateinit var binding: MainFlowFragmentBinding

    override fun bind(binding: MainFlowFragmentBinding) {
        this.binding = binding
        setupView()
    }

    private fun setupView() {
        val navController = navigation.findNavController(binding.mainFlowFragmentFNavHost)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigationRadio,
                R.id.navigationSchedule,
                R.id.navigationMenu,
            )
        )
        navigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration)
        binding.mainFragmentBnvMenu.setupWithNavController(navController)
    }

}