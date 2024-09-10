package com.ilizma.player.view.bind

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.resources.R
import com.ilizma.view.extensions.setOnReactiveClickListener
import com.ilizma.view.extensions.snackbar

class RadioScreenBinderImp(
) {

    private fun createOptionsMenu() {// TODO: pending
        binding.radioScreenTb.inflateMenu(com.ilizma.cast.view.R.menu.cast_controller)
        binding.radioScreenTb.setOnMenuItemClickListener { true }
        viewModel.setUpMediaRouteButton(
            menu = binding.radioScreenTb.menu,
            menuResourceId = com.ilizma.cast.view.R.id.media_route_menu_item,
        )
    }

}