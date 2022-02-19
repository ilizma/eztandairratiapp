package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.repository.DayNameRepository
import com.ilizma.schedule.domain.usecase.DayNameUseCase
import com.ilizma.schedule.domain.usecase.DayNameUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DayNameUseCaseModule {

    @Provides
    fun provideDayNameUseCase(
        repository: DayNameRepository
    ): DayNameUseCase = DayNameUseCaseImp(
        repository,
    )

}