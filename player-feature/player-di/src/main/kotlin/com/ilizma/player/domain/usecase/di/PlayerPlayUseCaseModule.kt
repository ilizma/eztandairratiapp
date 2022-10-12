package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.repository.PlayerRepository
import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import com.ilizma.player.domain.usecase.PlayerPlayUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object PlayerPlayUseCaseModule {

    @Provides
    fun providePlayerPlayUseCase(
        repository: PlayerRepository,
    ): PlayerPlayUseCase = PlayerPlayUseCaseImp(
        repository = repository,
    )

}