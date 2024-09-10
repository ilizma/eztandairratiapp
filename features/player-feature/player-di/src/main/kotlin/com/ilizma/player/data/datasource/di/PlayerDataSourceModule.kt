package com.ilizma.player.data.datasource.di

import com.ilizma.player.data.datasource.PlayerDataSource
import com.ilizma.player.data.datasource.PlayerDataSourceImp
import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.framework.PlayerFramework
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object PlayerDataSourceModule {

    @Provides
    fun providePlayerDataSource(
        framework: PlayerFramework,
    ): PlayerDataSource = PlayerDataSourceImp(
        framework = framework,
        mapper = PlayerStateMapper(),
    )

}