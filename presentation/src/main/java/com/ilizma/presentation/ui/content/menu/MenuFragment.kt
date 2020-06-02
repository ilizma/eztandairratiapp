package com.ilizma.presentation.ui.content.menu

import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.R
import com.ilizma.presentation.ui.base.BaseFragment
import dagger.Lazy
import javax.inject.Inject

class MenuFragment : BaseFragment() {

    @Inject
    override lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private lateinit var menuViewModel: MenuViewModel

    override var fragmentLayout = R.layout.fragment_menu


}