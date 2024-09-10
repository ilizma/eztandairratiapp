package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.MenuBackNavigator
import com.ilizma.menu.flow.navigator.MenuBackNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MenuBackNavigatorModule {

    @Provides
    fun provideMenuBackNavigator(
    ): MenuBackNavigator = MenuBackNavigatorImp()

}