package com.ilizma.schedule.view.bind.id

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilizma.schedule.view.adapter.factory.ProgramsAdapterFactory
import com.ilizma.schedule.view.bind.ScheduleDetailBinder
import com.ilizma.schedule.view.bind.ScheduleDetailBinderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ScheduleDetailBinderModule {

    @Provides
    fun provideScheduleDetailBinder(
        fragment: Fragment,
        adapterFactory: ProgramsAdapterFactory
    ): ScheduleDetailBinder = ScheduleDetailBinderImp(
        viewModelLazy = fragment.viewModels(),
        lifecycleOwner = { fragment.viewLifecycleOwner },
        adapterFactory = adapterFactory,
    )

}