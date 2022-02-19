package com.ilizma.player.view.bind.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.player.view.bind.RadioScreenBinder
import com.ilizma.player.view.bind.RadioScreenBinderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object RadioScreenBinderModule {

    @Provides
    fun provideRadioScreenBinder(
        fragment: Fragment,
        viewModelProviderFactory: ViewModelProvider.Factory
    ): RadioScreenBinder = RadioScreenBinderImp(
        viewModelLazy = fragment.viewModels { viewModelProviderFactory },
        lifecycleOwner = { fragment.viewLifecycleOwner },
    )

}