package com.ilizma.navigation.presentation.viewmodel.factory.di

import androidx.lifecycle.ViewModelProvider
import com.ilizma.navigation.presentation.viewmodel.factory.NavigationScreenViewModelAssistedFactory
import com.ilizma.navigation.presentation.viewmodel.factory.NavigationScreenViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

const val NAVIGATION_SCREEN_VIEW_MODEL_PROVIDER_NAMED =
    "NAVIGATION_SCREEN_VIEW_MODEL_PROVIDER_NAMED"

@Module
@InstallIn(ActivityComponent::class)
object NavigationScreenViewModelFactoryModule {

    @Provides
    @Named(NAVIGATION_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    fun provideNavigationScreenViewModelFactory(
        navigationScreenViewModelAssistedFactory: NavigationScreenViewModelAssistedFactory,
    ): ViewModelProvider.Factory = NavigationScreenViewModelFactory(
        navigationScreenViewModelAssistedFactory,
    )

}