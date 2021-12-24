package com.ilizma.player.view.bind.di

import com.ilizma.player.view.bind.RadioScreenBinder
import com.ilizma.player.view.bind.RadioScreenBinderImp
import com.ilizma.player.view.fragment.RadioScreenFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory

@Module
@InstallIn(FragmentComponent::class)
object RadioScreenBinderModule {

    @Provides
    fun provideRadioScreenBinder(
        fragment: RadioScreenFragment,
        viewModelFactory: HiltViewModelFactory,
    ): RadioScreenBinder = RadioScreenBinderImp(
        viewModelLazy = fragment.viewModels { viewModelFactory },
        lifecycleOwner = { fragment.viewLifecycleOwner },
    )

}