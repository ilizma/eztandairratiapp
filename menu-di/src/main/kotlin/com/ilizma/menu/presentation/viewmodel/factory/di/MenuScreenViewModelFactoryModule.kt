package com.ilizma.menu.presentation.viewmodel.factory.di

import androidx.lifecycle.ViewModelProvider
import com.ilizma.menu.presentation.viewmodel.factory.MenuScreenViewModelAssistedFactory
import com.ilizma.menu.presentation.viewmodel.factory.MenuScreenViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

const val MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED = "MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED"

@Module
@InstallIn(FragmentComponent::class)
object MenuScreenViewModelFactoryModule {

    @Provides
    @Named(MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    fun provideMenuScreenViewModelFactory(
        menuScreenViewModelAssistedFactory: MenuScreenViewModelAssistedFactory,
    ): ViewModelProvider.Factory = MenuScreenViewModelFactory(
        menuScreenViewModelAssistedFactory,
    )

}