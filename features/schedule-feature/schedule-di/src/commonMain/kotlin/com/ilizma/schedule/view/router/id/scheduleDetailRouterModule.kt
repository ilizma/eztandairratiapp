package com.ilizma.schedule.view.router.id

import com.ilizma.schedule.flow.router.ScheduleDetailRouterImp
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleDetailRouterModule: Module = module {

    factory<ScheduleDetailRouter> {
        ScheduleDetailRouterImp(
            closeNavigator = get(),
        )
    }

}