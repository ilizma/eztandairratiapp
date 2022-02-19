package com.ilizma.menu.view.bind.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilizma.menu.view.bind.MenuScreenFragmentBinder
import com.ilizma.menu.view.bind.MenuScreenFragmentBinderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object MenuScreenFragmentModule {

    @Provides
    fun provideMenuScreenFragmentBinder(
        fragment: Fragment,
    ): MenuScreenFragmentBinder = MenuScreenFragmentBinderImp(
        fragment.viewModels()
    )

}