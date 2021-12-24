package com.ilizma.player.view.fragment.di

import com.ilizma.player.view.fragment.RadioScreenFragment
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RadioScreenFragmentModule {

    @ContributesAndroidInjector(modules=[

    ])
    internal abstract fun bindRadioScreenFragment(): RadioScreenFragment
}