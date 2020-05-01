package com.ilizma.data.net

import com.ilizma.data.entity.base.DataConstants
import com.ilizma.data.repository.login.datasources.LoginLocalDataSource
import com.ilizma.domain.entity.base.Failure
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val loginLocalDataSource: LoginLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = loginLocalDataSource.getToken().onErrorResumeNext { throwable ->
            if (throwable is Failure.NotInDatabase) {
                Single.just("")
            } else {
                Single.error(throwable)
            }
        }.blockingGet()

        val request = chain.request()
        val requestBuilder = request.newBuilder()
            .method(request.method, request.body)

        if (accessToken.isNotEmpty()) {
            requestBuilder.header(DataConstants.ACCESS_TOKEN, accessToken)
        }

        return chain.proceed(requestBuilder.build())
    }

}