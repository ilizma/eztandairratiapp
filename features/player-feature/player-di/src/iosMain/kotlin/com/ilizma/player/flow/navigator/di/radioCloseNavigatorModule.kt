package com.ilizma.player.flow.navigator.di

import com.ilizma.player.flow.navigator.RadioCloseNavigator
import com.ilizma.player.flow.navigator.RadioCloseNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val radioCloseNavigatorModule: Module = module {

    factory<RadioCloseNavigator> { RadioCloseNavigatorImp() }

}