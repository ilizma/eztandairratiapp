package com.ilizma.eztandairratiapp.di.module.presentation

import com.ilizma.presentation.ui.content.MainActivity
import com.ilizma.eztandairratiapp.di.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun main(): MainActivity

}