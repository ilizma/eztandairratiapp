package com.ilizma.player.data.datasource.di

import com.ilizma.player.data.datasource.PlayerDataSource
import com.ilizma.player.data.datasource.PlayerDataSourceImp
import com.ilizma.player.data.mapper.PlayerStateMapper
import org.koin.core.module.Module
import org.koin.dsl.module

val playerDataSourceModule: Module = module {

    factory<PlayerDataSource> {
        PlayerDataSourceImp(
            framework = get(),
            mapper = PlayerStateMapper(),
        )
    }

}