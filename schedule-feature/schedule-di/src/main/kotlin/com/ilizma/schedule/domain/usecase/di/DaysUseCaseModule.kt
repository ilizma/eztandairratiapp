package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.repository.DaysRepository
import com.ilizma.schedule.domain.usecase.DaysUseCase
import com.ilizma.schedule.domain.usecase.DaysUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DaysUseCaseModule {

    @Provides
    fun provideDaysUseCase(
        repository: DaysRepository,
    ): DaysUseCase = DaysUseCaseImp(
        repository
    )

}