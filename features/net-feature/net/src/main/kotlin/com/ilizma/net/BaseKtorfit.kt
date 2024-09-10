package com.ilizma.net

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.FlowConverterFactory
import io.ktor.client.HttpClient

class BaseKtorfit(
    httpClient: HttpClient,
    baseUrl: String,
) {

    val ktorClient: Ktorfit = Ktorfit.Builder()
        .baseUrl(baseUrl)
        .httpClient(httpClient)
        .converterFactories(
            FlowConverterFactory(),
            CallConverterFactory(),
        )
        .build()

}