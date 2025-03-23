package com.ilizma.player.presentation.viewmodel.di

import androidx.activity.ComponentActivity
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import kotlin.also

actual val radioScreenViewModelModule: Module = module {

    scope<MainActivity> {
        viewModel<RadioScreenViewModel> {
            RadioScreenViewModelImp(
                stateUseCase = get(),
                playUseCase = get(),
                stopUseCase = get(),
                castFramework = get(),
                mapper = PlayerStateMapper(),
                _navigationAction = MutableSharedFlow(),
            ).also {
                @Suppress("UndeclaredKoinUsage")
                get<ComponentActivity>().lifecycle.addObserver(it)
            }
        }
    }

}