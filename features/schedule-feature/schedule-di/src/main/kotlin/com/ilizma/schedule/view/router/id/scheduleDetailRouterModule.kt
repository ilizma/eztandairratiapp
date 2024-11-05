package com.ilizma.schedule.view.router.id

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.schedule.flow.router.ScheduleDetailRouterImp
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleDetailRouterModule: Module = module {

    scope<MainActivity> {
        scoped<ScheduleDetailRouter> {
            ScheduleDetailRouterImp(
                lifecycleCoroutineScope = get<ComponentActivity>().lifecycleScope,
                closeNavigator = get(),
            )
        }
    }

}