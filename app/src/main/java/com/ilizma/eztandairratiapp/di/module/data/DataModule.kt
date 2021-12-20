package com.ilizma.eztandairratiapp.di.module.data

import dagger.Module

@Module(
    includes = [
        NetModule::class,
        RepositoryModule::class,
        ApiModule::class,
        SharedPreferencesModule::class,
    ]
)
class DataModule