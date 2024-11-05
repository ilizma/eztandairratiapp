package com.ilizma.schedule.flow.navigator.di

import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleDetailNavigatorModule: Module = module {

    factory<ScheduleDetailNavigator> { ScheduleDetailNavigatorImp() }

}