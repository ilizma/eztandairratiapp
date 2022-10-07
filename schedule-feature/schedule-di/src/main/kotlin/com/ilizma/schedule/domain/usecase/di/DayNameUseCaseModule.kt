package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.repository.DayNameRepository
import com.ilizma.schedule.domain.usecase.DayNameUseCase
import com.ilizma.schedule.domain.usecase.DayNameUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DayNameUseCaseModule {

    @Provides
    fun provideDayNameUseCase(
        repository: DayNameRepository
    ): DayNameUseCase = DayNameUseCaseImp(
        repository,
    )

}