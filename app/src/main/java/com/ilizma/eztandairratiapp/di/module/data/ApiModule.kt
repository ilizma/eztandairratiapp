package com.ilizma.eztandairratiapp.di.module.data

import com.ilizma.data.repository.login.data.LoginApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun login(retrofit: Retrofit): LoginApi =
        retrofit.create(LoginApi::class.java)

}