package com.ilizma.eztandairratiapp.di.module.data

import android.content.Context
import android.content.SharedPreferences
import com.ilizma.data.datasources.local.SharedPreferencesAssistant
import com.ilizma.data.datasources.local.SharedPreferencesKeys.LOGIN_PREF
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SharedPreferencesModule {

    @Provides
    @Named(LOGIN_PREF)
    fun login(context: Context): SharedPreferences =
        context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE)

    @Provides
    @Named(LOGIN_PREF)
    fun loginAssistant(
        @Named(LOGIN_PREF)
        sharedPreferences: SharedPreferences
    ): SharedPreferencesAssistant = SharedPreferencesAssistant(sharedPreferences)

}