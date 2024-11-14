package com.ilizma.schedule.view.router.id

import com.ilizma.schedule.flow.router.ScheduleScreenRouterImp
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleScreenRouterModule: Module = module {

    factory<ScheduleScreenRouter> {
        ScheduleScreenRouterImp(
            scheduleBackNavigator = get(),
            scheduleDetailNavigator = get(),
        )
    }

}