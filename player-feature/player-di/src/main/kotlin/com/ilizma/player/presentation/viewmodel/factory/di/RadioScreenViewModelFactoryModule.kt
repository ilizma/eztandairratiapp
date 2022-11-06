package com.ilizma.player.presentation.viewmodel.factory.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ilizma.player.presentation.viewmodel.factory.RadioScreenViewModelAssistedFactory
import com.ilizma.player.presentation.viewmodel.factory.RadioScreenViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

const val RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED = "RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED"

@Module
@InstallIn(FragmentComponent::class)
object RadioScreenViewModelFactoryModule {

    @Provides
    @Named(RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    fun provideRadioScreenViewModelFactory(
        fragment: Fragment,
        radioScreenViewModelAssistedFactory: RadioScreenViewModelAssistedFactory,
    ): ViewModelProvider.Factory = RadioScreenViewModelFactory(
        fragment = fragment,
        radioScreenViewModelAssistedFactory = radioScreenViewModelAssistedFactory,
    )

}