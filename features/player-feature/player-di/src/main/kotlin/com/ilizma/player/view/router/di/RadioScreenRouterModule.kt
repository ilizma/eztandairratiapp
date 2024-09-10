package com.ilizma.player.view.router.di

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ilizma.cast.flow.navigator.CastPlayerNavigator
import com.ilizma.player.flow.navigator.RadioCloseNavigator
import com.ilizma.player.flow.router.RadioScreenRouterImp
import com.ilizma.player.presentation.viewmodel.factory.di.RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.player.view.router.RadioScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object RadioScreenRouterModule {

    @Provides
    fun provideRadioScreenRouter(
        activity: Activity,
        radioCloseNavigator: RadioCloseNavigator,
        castPlayerNavigator: CastPlayerNavigator,
        @Named(RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): RadioScreenRouter = RadioScreenRouterImp(
        viewModelLazy = (activity as ComponentActivity).viewModels { viewModelProviderFactory },
        lifecycleCoroutineScope = activity.lifecycleScope,
        radioCloseNavigator = radioCloseNavigator,
        castPlayerNavigator = castPlayerNavigator,
    )

}