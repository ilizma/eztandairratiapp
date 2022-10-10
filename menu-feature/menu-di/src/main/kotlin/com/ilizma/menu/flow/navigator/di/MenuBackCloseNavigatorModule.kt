package com.ilizma.menu.flow.navigator.di

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilizma.menu.flow.navigator.MenuBackCloseNavigator
import com.ilizma.menu.flow.navigator.MenuBackCloseNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object MenuBackCloseNavigatorModule {

    @Provides
    fun provideMenuBackCloseNavigator(
        fragment: Fragment,
    ): MenuBackCloseNavigator = MenuBackCloseNavigatorImp(
        navController = fragment.findNavController(),
    )

}