package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.WhatsAppNavigator
import com.ilizma.menu.flow.navigator.WhatsAppNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val whatsAppNavigatorModule: Module = module {

    factory<WhatsAppNavigator> { WhatsAppNavigatorImp() }

}