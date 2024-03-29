package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.repository.PlayerRepository
import com.ilizma.player.domain.usecase.PlayerStateUseCase
import com.ilizma.player.domain.usecase.PlayerStateUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object PlayerStateUseCaseModule {

    @Provides
    fun providePlayerStateUseCase(
        repository: PlayerRepository,
    ): PlayerStateUseCase = PlayerStateUseCaseImp(
        repository = repository,
    )

}