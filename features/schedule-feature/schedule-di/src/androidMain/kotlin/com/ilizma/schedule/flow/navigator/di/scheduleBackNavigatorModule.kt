package com.ilizma.schedule.flow.navigator.di

import com.ilizma.schedule.flow.navigator.ScheduleBackNavigator
import com.ilizma.schedule.flow.navigator.ScheduleBackNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val scheduleBackNavigatorModule: Module = module {

    factory<ScheduleBackNavigator> { ScheduleBackNavigatorImp() }

}