package com.ilizma.app.view.activity.di

import android.content.Context
import android.content.Intent
import com.ilizma.app.view.activity.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainActivityIntentModule {

    @Provides
    @Singleton
    fun provideMainActivityIntent(
        @ApplicationContext context: Context,
    ): Intent = Intent(
        context,
        MainActivity::class.java,
    )

}