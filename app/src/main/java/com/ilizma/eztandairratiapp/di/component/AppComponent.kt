package com.ilizma.eztandairratiapp.di.component

import com.ilizma.eztandairratiapp.application.EztandaApplication
import com.ilizma.eztandairratiapp.di.module.data.DataModule
import com.ilizma.eztandairratiapp.di.module.presentation.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DataModule::class,
        PresentationModule::class,
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