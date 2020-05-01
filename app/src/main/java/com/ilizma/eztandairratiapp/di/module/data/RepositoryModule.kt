package com.ilizma.eztandairratiapp.di.module.data

import com.ilizma.data.repository.login.LoginRepositoryImpl
import com.ilizma.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun login(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

}