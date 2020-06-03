package com.ilizma.presentation.ui.content.menu

import android.os.Bundle
import android.view.View
import com.ilizma.presentation.R
import com.ilizma.presentation.ui.base.BaseFragment
import com.ilizma.presentation.ui.content.MainActivity

class MenuFragment : BaseFragment() {

    private lateinit var menuViewModel: MenuViewModel

    override var fragmentLayout = R.layout.fragment_menu

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.title_menu)
    }

}