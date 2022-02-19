package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.repository.ScheduleRepository
import com.ilizma.schedule.domain.usecase.ScheduleUseCase
import com.ilizma.schedule.domain.usecase.ScheduleUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ScheduleUseCaseModule {

    @Provides
    fun provideScheduleUseCase(
        repository: ScheduleRepository,
    ): ScheduleUseCase = ScheduleUseCaseImp(
        repository,
    )

}