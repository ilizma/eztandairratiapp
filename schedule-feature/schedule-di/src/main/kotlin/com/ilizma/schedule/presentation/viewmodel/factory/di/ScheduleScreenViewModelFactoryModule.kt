package com.ilizma.schedule.presentation.viewmodel.factory.di

import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.presentation.viewmodel.factory.ScheduleScreenViewModelAssistedFactory
import com.ilizma.schedule.presentation.viewmodel.factory.ScheduleScreenViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

const val SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED = "SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED"

@Module
@InstallIn(FragmentComponent::class)
object ScheduleScreenViewModelFactoryModule {

    @Provides
    @Named(SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    fun provideScheduleScreenViewModelFactory(
        scheduleScreenViewModelAssistedFactory: ScheduleScreenViewModelAssistedFactory,
    ): ViewModelProvider.Factory = ScheduleScreenViewModelFactory(
        scheduleScreenViewModelAssistedFactory,
    )

}