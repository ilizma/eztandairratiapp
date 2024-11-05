package com.ilizma.api.data.di

import com.ilizma.api.data.EztandaApi
import com.ilizma.api.data.createEztandaApi
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.dsl.module

val eztandaApiModule: Module = module {

    single<EztandaApi> { get<Ktorfit>().createEztandaApi() }

}