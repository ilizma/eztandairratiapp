package com.ilizma.menu.view.bind

import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.databinding.MenuScreenFragmentBinding
import com.ilizma.view.extensions.setOnReactiveClickListener

class MenuScreenFragmentBinderImp(
    viewModelLazy: Lazy<MenuScreenViewModel>,
) : MenuScreenFragmentBinder {

    private val viewModel: MenuScreenViewModel by viewModelLazy
    private lateinit var binding: MenuScreenFragmentBinding

    override fun bind(binding: MenuScreenFragmentBinding) {
        this.binding = binding
        setupView()
    }

    private fun setupView() {
        // TODO: 03/06/2020 get url and phones from service
        binding.menuFragmentTvTwitter.setOnReactiveClickListener {
            viewModel.onTwitter()
        }
        binding.menuFragmentTvFacebook.setOnReactiveClickListener {
            viewModel.onFacebook()
        }
        binding.menuFragmentTvPhone.setOnReactiveClickListener {
            viewModel.onPhone()
        }
        binding.menuFragmentTvWeb.setOnReactiveClickListener {
            viewModel.onWeb()
        }
    }

}