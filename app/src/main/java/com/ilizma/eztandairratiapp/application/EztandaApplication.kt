package com.ilizma.eztandairratiapp.application

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class EztandaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.DebugTree()
            .let { Timber.plant(it) }
        CaocConfig.Builder.create()
            .errorActivity(EztandaCrashActivity::class.java)
            .apply()
    }

}