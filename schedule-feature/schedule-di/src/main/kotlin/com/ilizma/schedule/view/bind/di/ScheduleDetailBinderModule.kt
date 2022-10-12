package com.ilizma.schedule.view.bind.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.schedule.view.adapter.factory.ProgramsAdapterFactoryImp
import com.ilizma.schedule.view.bind.ScheduleDetailBinder
import com.ilizma.schedule.view.bind.ScheduleDetailBinderImp
import com.ilizma.view.adapter.factory.AdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object ScheduleDetailBinderModule {

    @Provides
    fun provideScheduleDetailBinder(
        fragment: Fragment,
        adapterFactory: AdapterFactory<ProgramType>,
        @Named(SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): ScheduleDetailBinder = ScheduleDetailBinderImp(
        viewModelLazy = fragment.viewModels { viewModelProviderFactory },
        lifecycleOwner = { fragment.viewLifecycleOwner },
        adapterFactory = adapterFactory,
    )

}