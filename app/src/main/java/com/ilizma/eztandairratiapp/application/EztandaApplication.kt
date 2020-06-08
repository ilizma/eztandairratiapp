package com.ilizma.eztandairratiapp.application

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.ilizma.eztandairratiapp.di.component.DaggerAppComponent
import com.ilizma.presentation.ui.customview.EztandaCrashActivity
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class EztandaApplication : DaggerApplication(), LifecycleObserver {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        Timber.plant(Timber.DebugTree())
        CaocConfig.Builder.create()
            .errorActivity(EztandaCrashActivity::class.java)
            .apply()
    }

}