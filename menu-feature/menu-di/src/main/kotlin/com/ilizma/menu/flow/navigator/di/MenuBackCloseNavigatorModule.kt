package com.ilizma.menu.flow.navigator.di

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
    ): MenuBackCloseNavigator = MenuBackCloseNavigatorImp()

}