package com.ilizma.data.repository.login.datasources

import com.ilizma.data.datasources.local.BaseLocalDataSource
import com.ilizma.data.datasources.local.SharedPreferencesAssistant
import com.ilizma.data.datasources.local.SharedPreferencesKeys.LOGIN_PREF
import com.ilizma.data.datasources.local.SharedPreferencesKeys.TOKEN_KEY
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class LoginLocalDataSource @Inject constructor(
    @Named(LOGIN_PREF)
    private val assistant: SharedPreferencesAssistant
) : BaseLocalDataSource() {

    fun saveToken(token: String) =
        assistant.saveString(TOKEN_KEY, token)

    fun getToken(): Single<String> =
        assistant.getString(TOKEN_KEY)

    fun nuke(): Completable =
        assistant.nuke()

}