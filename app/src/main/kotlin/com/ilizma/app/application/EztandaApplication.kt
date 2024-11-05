package com.ilizma.app.application

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.ilizma.app.di.initKoin
import com.ilizma.errormanagement.view.activity.CrashActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class EztandaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@EztandaApplication)
        }
        CaocConfig.Builder.create()
            .errorActivity(CrashActivity::class.java)
            .apply()
    }

}