package com.ilizma.cast.framework.di

import android.content.res.Resources
import com.ilizma.cast.framework.CastFramework
import com.ilizma.cast.framework.CastFrameworkImp
import com.ilizma.cast.framework.listener.CastStateListenerImp
import com.ilizma.cast.framework.listener.SessionManagerListenerImp
import com.ilizma.cast.framework.model.CastState.DISCONNECTED
import com.ilizma.resources.R
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val castFrameworkModule: Module = module {

    factory<CastFramework> {
        CastFrameworkImp(
            context = androidContext(),
            _castStateFlow = MutableStateFlow(DISCONNECTED),
            castStateListener = CastStateListenerImp(),
            sessionManagerListener = SessionManagerListenerImp(),
            title = get<Resources>().getString(R.string.radio_name),
            subtitle = get<Resources>().getString(R.string.free_radio),
            image = "https://www.eztanda.com/images/eztanda_logo.png",
            playerFramework = get(),
        )
    }

}