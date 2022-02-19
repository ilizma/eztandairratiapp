package com.ilizma.player.view.router.di

import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilizma.player.flow.navigator.RadioBackCloseNavigator
import com.ilizma.player.flow.router.RadioScreenRouterImp
import com.ilizma.player.view.router.RadioScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object RadioScreenRouterModule {

    @Provides
    fun provideRadioScreenRouter(
        fragment: Fragment,
        radioBackCloseNavigator: RadioBackCloseNavigator,
    ): RadioScreenRouter = RadioScreenRouterImp(
        lifecycleOwner = { fragment.viewLifecycleOwner },
        onBackPressedDispatcher = OnBackPressedDispatcher(),
        viewModelLazy = fragment.viewModels(),
        radioBackCloseNavigator = radioBackCloseNavigator,
    )

}