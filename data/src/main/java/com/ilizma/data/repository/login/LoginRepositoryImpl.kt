package com.ilizma.data.repository.login

import com.ilizma.data.repository.login.datasources.LoginLocalDataSource
import com.ilizma.data.repository.login.datasources.LoginRemoteDataSource
import com.ilizma.domain.repository.LoginRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
) : LoginRepository {

    override fun login(): Completable =
        loginRemoteDataSource.login()
            .doOnSuccess { token ->
                loginLocalDataSource.saveToken(token)
            }
            .ignoreElement()

    override fun nuke(): Completable =
        loginLocalDataSource.nuke()

}