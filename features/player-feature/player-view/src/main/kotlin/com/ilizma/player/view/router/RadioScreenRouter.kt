package com.ilizma.player.view.router

import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface RadioScreenRouter {

    fun init(
        viewModel: RadioScreenViewModel,
        coroutineScope: CoroutineScope,
    )

}