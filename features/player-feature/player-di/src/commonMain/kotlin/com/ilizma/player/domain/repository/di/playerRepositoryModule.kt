package com.ilizma.player.domain.repository.di

import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.data.repository.PlayerRepositoryImp
import com.ilizma.player.domain.repository.PlayerRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val playerRepositoryModule: Module = module {

    factory<PlayerRepository> {
        PlayerRepositoryImp(
            dataSource = get(),
            mapper = PlayerStateMapper(),
        )
    }

}