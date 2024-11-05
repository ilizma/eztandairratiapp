package com.ilizma.net.di

import com.ilizma.net.BaseKtorfit
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.dsl.module

val ktorfitModule: Module = module {

    single<Ktorfit> {
        get<BaseKtorfit>().ktorClient
    }

}