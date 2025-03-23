package com.ilizma.app.application

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.ilizma.app.di.initKoin
import com.ilizma.cast.di.castModules
import com.ilizma.errormanagement.di.errorManagementModules
import com.ilizma.errormanagement.view.activity.CrashActivity
import com.ilizma.main.di.mainModules
import com.ilizma.app.BuildConfig
import com.ilizma.review.di.reviewModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.core.module.Module

class EztandaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            isDebug = BuildConfig.DEBUG,
            config = {
                androidLogger(Level.DEBUG)
                androidContext(this@EztandaApplication)
            },
            platformModules = mutableListOf<Module>()
                .apply {
                    addAll(mainModules)
                    addAll(castModules)
                    addAll(errorManagementModules)
                    addAll(reviewModules)
                }
        )

        CaocConfig.Builder.create()
            .errorActivity(CrashActivity::class.java)
            .apply()
    }

}