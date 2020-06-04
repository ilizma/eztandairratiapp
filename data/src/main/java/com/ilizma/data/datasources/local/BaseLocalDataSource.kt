package com.ilizma.data.datasources.local

import androidx.annotation.VisibleForTesting
import com.ilizma.domain.entity.base.Failure
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Lazy
import io.reactivex.Single
import java.io.EOFException
import java.io.IOException

abstract class BaseLocalDataSource {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    abstract var moshi: Lazy<Moshi>

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    inline fun <reified T : Any> T.toJson(): String {
        val jsonAdapter = moshi.get().adapter(T::class.java)
        return jsonAdapter.toJson(this)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    inline fun <reified T> Single<String>.fromJson(): Single<T> {
        return try {
            val jsonAdapter = moshi.get().adapter(T::class.java)
            val t = jsonAdapter.fromJson(blockingGet())
            Single.just(t)
        } catch (e: Exception) {
            if (e is EOFException ||
                e is IOException ||
                e.message?.contains("NotInDatabase", true) == true
            ) {
                Single.error(Failure.NotInDatabase)
            } else {
                Single.error(e)
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    inline fun <reified T> Single<String>.fromJsonList(): Single<List<T>> {
        val listMyData = Types.newParameterizedType(List::class.java, T::class.java)
        val jsonAdapter = moshi.get().adapter<List<T>>(listMyData)
        return try {
            val t = jsonAdapter.fromJson(blockingGet())
            Single.just(t)
        } catch (e: Exception) {
            if (e is EOFException ||
                e is IOException ||
                e.message?.contains("NotInDatabase", true) == true
            ) {
                Single.error(Failure.NotInDatabase)
            } else {
                Single.error(e)
            }
        }
    }

}
