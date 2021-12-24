package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.repository.PlayerRepository
import com.ilizma.player.domain.usecase.PlayerUseCase
import com.ilizma.player.domain.usecase.PlayerUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object PlayerUseCaseModule {

    @Provides
    fun providePlayerUseCase(
        repository: PlayerRepository,
    ): PlayerUseCase = PlayerUseCaseImp(
        repository = repository,
    )
}