package com.ilizma.schedule.view.bind.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.schedule.view.bind.ScheduleScreenBinder
import com.ilizma.schedule.view.bind.ScheduleScreenBinderImp
import com.ilizma.view.adapter.factory.AdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object ScheduleScreenBinderModule {

    @Provides
    fun provideScheduleScreenBinder(
        fragment: Fragment,
        adapterFactory: AdapterFactory<Day>,
        @Named(SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): ScheduleScreenBinder = ScheduleScreenBinderImp(
        viewModelLazy = fragment.viewModels { viewModelProviderFactory },
        lifecycleOwner = { fragment.viewLifecycleOwner },
        adapterFactory = adapterFactory,
    )

}