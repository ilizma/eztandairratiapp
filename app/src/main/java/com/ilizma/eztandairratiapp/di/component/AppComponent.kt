package com.ilizma.eztandairratiapp.di.component

import com.ilizma.eztandairratiapp.application.EztandaApplication
import com.ilizma.eztandairratiapp.di.module.presentation.ActivityModule
import com.ilizma.eztandairratiapp.di.module.presentation.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
    ]
)
interface AppComponent : AndroidInjector<EztandaApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(
            app: EztandaApplication,
        ): Builder

        fun build(): AppComponent
    }

}