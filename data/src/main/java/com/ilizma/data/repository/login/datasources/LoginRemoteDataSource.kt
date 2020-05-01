package com.ilizma.data.repository.login.datasources

import com.ilizma.data.datasources.remote.BaseRemoteDataSource
import com.ilizma.data.repository.login.data.LoginApi
import com.ilizma.domain.entity.base.Success
import io.reactivex.Single
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val loginApi: LoginApi
) : BaseRemoteDataSource() {

    fun login(): Single<String> {
        return modifySingle(loginApi.login())
    }

}