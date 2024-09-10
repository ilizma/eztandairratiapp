package com.ilizma.net.di

import com.ilizma.net.BaseKtorfit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.jensklingenberg.ktorfit.Ktorfit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorfitModule {

    @Provides
    @Singleton
    fun provideKtorfit(
        baseKtorfit: BaseKtorfit,
    ): Ktorfit = baseKtorfit.ktorClient

}