package com.ilizma.app.di

import com.ilizma.api.di.apiModules
import com.ilizma.cast.di.castModules
import com.ilizma.errormanagement.di.errorManagementModules
import com.ilizma.main.di.mainModules
import com.ilizma.menu.di.menuModules
import com.ilizma.net.di.netModules
import com.ilizma.player.di.playerModules
import com.ilizma.review.di.reviewModules
import com.ilizma.schedule.di.scheduleModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    config: KoinAppDeclaration? = null,
) {
    startKoin {
        config?.invoke(this)
        mutableListOf<Module>()
            .apply {
                addAll(apiModules)
                addAll(castModules)
                addAll(errorManagementModules)
                addAll(mainModules)
                addAll(menuModules)
                addAll(netModules)
                addAll(playerModules)
                addAll(reviewModules)
                addAll(scheduleModules)
            }.let { modules(it) }
    }
}