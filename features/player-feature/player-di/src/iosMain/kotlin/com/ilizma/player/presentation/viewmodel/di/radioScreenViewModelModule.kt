package com.ilizma.player.presentation.viewmodel.di

import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val radioScreenViewModelModule: Module = module {

    viewModel<RadioScreenViewModel> {
        RadioScreenViewModelImp(
            stateUseCase = get(),
            playUseCase = get(),
            stopUseCase = get(),
            mapper = PlayerStateMapper(),
            _navigationAction = MutableSharedFlow(),
        )
    }

}