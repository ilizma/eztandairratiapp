package com.ilizma.schedule.view.router.id

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.schedule.flow.router.ScheduleScreenRouterImp
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleScreenRouterModule: Module = module {

    scope<MainActivity> {
        scoped<ScheduleScreenRouter> {
            ScheduleScreenRouterImp(
                lifecycleCoroutineScope = get<ComponentActivity>().lifecycleScope,
                scheduleBackNavigator = get(),
                scheduleDetailNavigator = get(),
            )
        }
    }

}