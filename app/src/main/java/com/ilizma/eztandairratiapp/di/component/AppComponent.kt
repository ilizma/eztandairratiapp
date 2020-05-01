package com.ilizma.eztandairratiapp.di.component

import com.ilizma.eztandairratiapp.application.BaseApplication
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
        PresentationModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: BaseApplication): Builder

        fun build(): AppComponent
    }

}