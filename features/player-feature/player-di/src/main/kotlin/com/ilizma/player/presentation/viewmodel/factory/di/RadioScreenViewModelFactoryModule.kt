package com.ilizma.player.presentation.viewmodel.factory.di

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.ilizma.player.presentation.viewmodel.factory.RadioScreenViewModelAssistedFactory
import com.ilizma.player.presentation.viewmodel.factory.RadioScreenViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

const val RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED = "RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED"

@Module
@InstallIn(ActivityComponent::class)
object RadioScreenViewModelFactoryModule {

    @Provides
    @Named(RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    fun provideRadioScreenViewModelFactory(
        activity: Activity,
        radioScreenViewModelAssistedFactory: RadioScreenViewModelAssistedFactory,
    ): ViewModelProvider.Factory = RadioScreenViewModelFactory(
        activity = activity,
        radioScreenViewModelAssistedFactory = radioScreenViewModelAssistedFactory,
    )

}