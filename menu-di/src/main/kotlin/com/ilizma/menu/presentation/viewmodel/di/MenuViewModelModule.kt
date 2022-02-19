package com.ilizma.menu.presentation.viewmodel.di

import com.ilizma.di.viewmodel.ViewModelKey
import com.ilizma.menu.presentation.viewmodel.MenuViewModel
import com.ilizma.menu.presentation.viewmodel.MenuViewModelImp
import com.ilizma.presentation.SingleLiveEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
object MenuViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    fun provideMenuViewModel(
    ): MenuViewModel = MenuViewModelImp(
        _navigationAction = SingleLiveEvent(),
    )

}