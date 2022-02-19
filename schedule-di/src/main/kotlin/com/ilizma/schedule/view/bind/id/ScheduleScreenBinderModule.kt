package com.ilizma.schedule.view.bind.id

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilizma.schedule.view.adapter.factory.DaysAdapterFactory
import com.ilizma.schedule.view.bind.ScheduleScreenBinder
import com.ilizma.schedule.view.bind.ScheduleScreenBinderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ScheduleScreenBinderModule {

    @Provides
    fun provideScheduleScreenBinder(
        fragment: Fragment,
        adapterFactory: DaysAdapterFactory,
    ): ScheduleScreenBinder = ScheduleScreenBinderImp(
        viewModelLazy = fragment.viewModels(),
        lifecycleOwner = { fragment.viewLifecycleOwner },
        adapterFactory = adapterFactory,
    )

}