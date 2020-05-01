package com.ilizma.presentation.ui.content.home

import android.os.Bundle
import android.view.View
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.observe
import com.ilizma.presentation.extensions.viewModel
import com.ilizma.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel

    override var fragmentLayout = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = viewModel(viewModelFactory.get()) {
            observe(text) {
                textHome.text = it
            }
        }
    }

}
