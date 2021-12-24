package com.ilizma.eztandairratiapp.di.module.presentation

import com.ilizma.eztandairratiapp.di.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun eztandaCrash(): EztandaCrashActivity

    @PerView
    @ContributesAndroidInjector
    abstract fun main(): MainActivity

}