package com.ilizma.data.datasources.local

import android.content.SharedPreferences
import com.ilizma.domain.entity.base.Failure
import io.reactivex.Completable
import io.reactivex.Single

const val INT_DEFAULT_VALUE = -1
const val STRING_DEFAULT_VALUE = "NO_VALUE"
const val BOOLEAN_DEFAULT_VALUE = false

class SharedPreferencesAssistant(
    private val sharedPreferences: SharedPreferences
) {

    fun saveString(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

    fun getString(key: String, defaultValue: String = STRING_DEFAULT_VALUE): Single<String> =
        Single.just(sharedPreferences.getString(key, defaultValue) ?: defaultValue)
            .doOnSuccess { if (it == STRING_DEFAULT_VALUE) throw Failure.NotInDatabase }

    fun saveBoolean(key: String, value: Boolean) =
        sharedPreferences.edit().putBoolean(key, value).apply()

    fun getBoolean(key: String, defValue: Boolean = BOOLEAN_DEFAULT_VALUE): Single<Boolean> =
        Single.just(sharedPreferences.getBoolean(key, defValue))

    fun saveInt(key: String, value: Int) =
        sharedPreferences.edit().putInt(key, value).apply()

    fun getInt(key: String): Single<Int> =
        Single.just(sharedPreferences.getInt(key, INT_DEFAULT_VALUE))
            .doOnSuccess { if (it == INT_DEFAULT_VALUE) throw Failure.NotInDatabase }

    fun removeKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun nuke(): Completable = Completable.create { emitter ->
        try {
            sharedPreferences.edit().clear().apply()
            emitter.onComplete()
        } catch (e: Exception) {
            emitter.tryOnError(e)
        }
    }

}