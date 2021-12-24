package com.ilizma.menu.view.bind

import com.ilizma.menu.presentation.viewmodel.MenuViewModel
import com.ilizma.menu.view.databinding.MenuScreenFragmentBinding
import com.ilizma.menu.view.imp.R
import com.ilizma.view.extensions.setOnReactiveClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuScreenFragmentBinderImp(
    viewModelLazy: Lazy<MenuViewModel>,
) : MenuScreenFragmentBinder {

    private val viewModel: MenuViewModel by viewModelLazy
    private lateinit var binding: MenuScreenFragmentBinding

    override fun bind(binding: MenuScreenFragmentBinding) {
        this.binding = binding
        setUpToolBar()
        setupView()
    }

    private fun setUpToolBar() {
        binding.mainFragmentToolbar.title = binding.root.context.getString(R.string.title_radio)
    }

    private fun setupView() {
        // TODO: 03/06/2020 get url and phones from service
        binding.menuFragmentTvTwitter.setOnReactiveClickListener {
            viewModel.onTwitter()
        }
        binding.menuFragmentTvFacebook.setOnReactiveClickListener {
            viewModel.onFacebook()
        }
        binding.menuFragmentTvWhatsapp.setOnReactiveClickListener {
            viewModel.onWhatsapp()
        }
        binding.menuFragmentTvPhone.setOnReactiveClickListener {
            viewModel.onPhone()
        }
        binding.menuFragmentTvWeb.setOnReactiveClickListener {
            viewModel.onWeb()
        }
    }

}