package com.ilizma.player.domain.repository.di

import com.ilizma.player.data.datasource.PlayerDataSource
import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.data.repository.PlayerRepositoryImp
import com.ilizma.player.domain.repository.PlayerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object PlayerRepositoryModule {

    @Provides
    fun providePlayerRepository(
        dataSource: PlayerDataSource,
    ): PlayerRepository = PlayerRepositoryImp(
        dataSource = dataSource,
        mapper = PlayerStateMapper(),
    )

}