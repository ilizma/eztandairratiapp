package com.ilizma.schedule.view.bind.id

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.schedule.view.adapter.factory.DaysAdapterFactory
import com.ilizma.schedule.view.bind.ScheduleScreenBinder
import com.ilizma.schedule.view.bind.ScheduleScreenBinderImp
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
        adapterFactory: DaysAdapterFactory,
        @Named(SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): ScheduleScreenBinder = ScheduleScreenBinderImp(
        viewModelLazy = fragment.viewModels { viewModelProviderFactory },
        lifecycleOwner = { fragment.viewLifecycleOwner },
        adapterFactory = adapterFactory,
    )

}