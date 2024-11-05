package com.ilizma.menu.presentation.viewmodel.di

import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModelImp
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val menuScreenViewModelModule: Module = module {

    viewModel<MenuScreenViewModel> {
        MenuScreenViewModelImp(
            _navigationAction = MutableSharedFlow(),
        )
    }

}