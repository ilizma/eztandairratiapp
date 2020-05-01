package com.ilizma.data.repository.login.data

import com.ilizma.data.entity.token.TokenResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET

interface LoginApi {

    @GET("login")
    fun login(): Single<Result<TokenResponse>>

}