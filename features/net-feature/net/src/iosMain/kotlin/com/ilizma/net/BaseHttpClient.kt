package com.ilizma.net

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class BaseHttpClient {

    private val darwinEngine = Darwin.create()

    val httpClient: HttpClient = HttpClient(darwinEngine) {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
    }

}