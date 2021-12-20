package com.ilizma.eztandairratiapp.di.module.data

import android.content.Context
import android.content.SharedPreferences
import com.ilizma.data.datasources.local.SharedPreferencesAssistant
import com.ilizma.data.datasources.local.SharedPreferencesKeys.SCHEDULE_PREF
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SharedPreferencesModule {

    @Provides
    @Named(SCHEDULE_PREF)
    fun schedule(
        context: Context,
    ): SharedPreferences = context.getSharedPreferences(SCHEDULE_PREF, Context.MODE_PRIVATE)

    @Provides
    @Named(SCHEDULE_PREF)
    fun scheduleAssistant(
        @Named(SCHEDULE_PREF)
        sharedPreferences: SharedPreferences,
    ): SharedPreferencesAssistant = SharedPreferencesAssistant(sharedPreferences)

}