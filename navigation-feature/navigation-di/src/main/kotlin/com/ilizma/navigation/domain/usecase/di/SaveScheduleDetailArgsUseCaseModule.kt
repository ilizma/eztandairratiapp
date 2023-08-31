package com.ilizma.navigation.domain.usecase.di

import com.ilizma.navigation.domain.repository.ScheduleDetailRepository
import com.ilizma.navigation.domain.usecase.SaveScheduleDetailArgsUseCase
import com.ilizma.navigation.domain.usecase.SaveScheduleDetailArgsUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object SaveDetailArgsUseCaseModule {

    @Provides
    fun provideSaveScheduleDetailArgsUseCase(
        repository: ScheduleDetailRepository,
    ): SaveScheduleDetailArgsUseCase = SaveScheduleDetailArgsUseCaseImp(
        repository,
    )

}