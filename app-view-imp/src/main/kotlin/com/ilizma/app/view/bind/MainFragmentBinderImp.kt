package com.ilizma.app.view.bind

import com.ilizma.app.view.databinding.MainFragmentBinding
import com.ilizma.app.view.imp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragmentBinderImp : MainFragmentBinder {

    private lateinit var binding: MainFragmentBinding

    override fun bind(binding: MainFragmentBinding) {
        this.binding = binding
        setUpToolBar()
    }

    private fun setUpToolBar() {
        binding.mainFragmentToolbar.title = binding.root.context.getString(R.string.title_radio)
    }

}