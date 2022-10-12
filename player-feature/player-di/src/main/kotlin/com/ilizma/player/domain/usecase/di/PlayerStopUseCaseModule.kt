package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.repository.PlayerRepository
import com.ilizma.player.domain.usecase.PlayerStopUseCase
import com.ilizma.player.domain.usecase.PlayerStopUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object PlayerStopUseCaseModule {

    @Provides
    fun providePlayerStopUseCase(
        repository: PlayerRepository,
    ): PlayerStopUseCase = PlayerStopUseCaseImp(
        repository = repository,
    )

}