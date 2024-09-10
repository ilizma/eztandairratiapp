package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.repository.ScheduleDetailArgsRepository
import com.ilizma.schedule.domain.usecase.SaveScheduleDetailArgsUseCase
import com.ilizma.schedule.domain.usecase.SaveScheduleDetailArgsUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object SaveScheduleDetailArgsUseCaseModule {

    @Provides
    fun provideSaveScheduleDetailArgsUseCase(
        repository: ScheduleDetailArgsRepository
    ): SaveScheduleDetailArgsUseCase = SaveScheduleDetailArgsUseCaseImp(
        repository,
    )

}