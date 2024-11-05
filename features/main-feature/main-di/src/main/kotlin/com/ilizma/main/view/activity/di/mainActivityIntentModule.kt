package com.ilizma.main.view.activity.di

import android.content.Intent
import com.ilizma.main.view.activity.MainActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val mainActivityIntentModule: Module = module {

    single<Intent> {
        Intent(
            androidContext(),
            MainActivity::class.java,
        )
    }

}