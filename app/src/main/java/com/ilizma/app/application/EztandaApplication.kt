package com.ilizma.app.application

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.ilizma.errormanagement.view.activity.CrashActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EztandaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CaocConfig.Builder.create()
            .errorActivity(CrashActivity::class.java)
            .apply()
    }

}