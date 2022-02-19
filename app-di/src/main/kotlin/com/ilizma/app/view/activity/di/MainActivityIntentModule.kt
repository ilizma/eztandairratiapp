package com.ilizma.app.view.activity.di

import android.content.Context
import android.content.Intent
import com.ilizma.app.view.activity.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainActivityIntentModule {

    @Provides
    fun provideMainActivityIntent(
        @ApplicationContext context: Context,
    ): Intent = Intent(
        context,
        MainActivity::class.java
    )

}