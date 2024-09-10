package com.ilizma.net

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 30L

class BaseHttpClient(
    chuckerCollector: ChuckerCollector,
    context: Context,
) {

    private val chuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(chuckerCollector)
        .build()

    private val okhttpEngine = OkHttp.create {
        addInterceptor(chuckerInterceptor)
    }

    val httpClient: HttpClient = HttpClient(okhttpEngine) {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = TimeUnit.SECONDS.toMillis(TIMEOUT)
            connectTimeoutMillis = TimeUnit.SECONDS.toMillis(TIMEOUT)
            socketTimeoutMillis = TimeUnit.SECONDS.toMillis(TIMEOUT)
        }
    }

}