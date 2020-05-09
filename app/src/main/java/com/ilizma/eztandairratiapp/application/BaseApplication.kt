package com.ilizma.eztandairratiapp.application

import cat.ereza.customactivityoncrash.config.CaocConfig
import com.ilizma.eztandairratiapp.di.component.DaggerAppComponent
import com.ilizma.presentation.ui.customview.EztandaCrashActivity
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        CaocConfig.Builder.create()
            .errorActivity(EztandaCrashActivity::class.java)
            .apply()
    }

}