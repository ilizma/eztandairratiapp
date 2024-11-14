package com.ilizma.schedule.flow.navigator.di

import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleDetailCloseNavigatorModule: Module = module {

    factory<ScheduleDetailCloseNavigator> { ScheduleDetailCloseNavigatorImp() }

}