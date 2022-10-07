package com.ilizma.schedule.presentation.viewmodel.factory.di

import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.presentation.viewmodel.factory.ScheduleDetailViewModelAssistedFactory
import com.ilizma.schedule.presentation.viewmodel.factory.ScheduleDetailViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named
import com.ilizma.resources.R

const val SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED = "SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED"

@Module
@InstallIn(FragmentComponent::class)
object ScheduleDetailViewModelFactoryModule {

    @Provides
    @Named(SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED)
    fun provideScheduleDetailViewModelFactory(
        scheduleDetailViewModelAssistedFactory: ScheduleDetailViewModelAssistedFactory,
        resources: Resources,
    ): ViewModelProvider.Factory = ScheduleDetailViewModelFactory(
        scheduleDetailViewModelAssistedFactory = scheduleDetailViewModelAssistedFactory,
        unknownErrorMessage = resources.getString(R.string.unknown_error),
    )

}