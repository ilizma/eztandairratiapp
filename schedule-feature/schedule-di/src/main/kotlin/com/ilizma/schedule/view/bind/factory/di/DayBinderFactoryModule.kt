package com.ilizma.schedule.view.bind.factory.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.schedule.view.bind.factory.DayBinderFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object DayBinderFactoryModule {

    @Provides
    fun provideDayBinderFactory(
        fragment: Fragment,
        @Named(SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): DayBinderFactory = DayBinderFactory(
        fragment.viewModels { viewModelProviderFactory },
    )

}