package com.ilizma.eztandairratiapp.di.module.presentation

import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        AppModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        MediaModule::class,
    ]
)
class PresentationModule