package com.ilizma.player.view.router.di

import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.player.flow.navigator.RadioBackCloseNavigator
import com.ilizma.player.flow.router.RadioScreenRouterImp
import com.ilizma.player.presentation.viewmodel.factory.di.RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.player.view.router.RadioScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object RadioScreenRouterModule {

    @Provides
    fun provideRadioScreenRouter(
        fragment: Fragment,
        radioBackCloseNavigator: RadioBackCloseNavigator,
        @Named(RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): RadioScreenRouter = RadioScreenRouterImp(
        lifecycleOwner = { fragment.viewLifecycleOwner },
        onBackPressedDispatcher = OnBackPressedDispatcher(),
        viewModelLazy = fragment.viewModels { viewModelProviderFactory },
        radioBackCloseNavigator = radioBackCloseNavigator,
    )

}