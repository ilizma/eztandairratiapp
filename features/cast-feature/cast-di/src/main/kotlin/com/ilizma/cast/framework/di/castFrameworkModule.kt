package com.ilizma.cast.framework.di

import android.content.res.Resources
import com.ilizma.cast.framework.CastFramework
import com.ilizma.cast.framework.listener.CastStateListener
import com.ilizma.cast.framework.listener.SessionManagerListener
import com.ilizma.cast.framework.model.CastState.DISCONNECTED
import com.ilizma.resources.R
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val castFrameworkModule: Module = module {

    factory<CastFramework> {
        CastFramework(
            context = androidContext(),
            _castStateFlow = MutableStateFlow(DISCONNECTED),
            castStateListener = CastStateListener(),
            sessionManagerListener = SessionManagerListener(),
            title = get<Resources>().getString(R.string.radio_name),
            subtitle = get<Resources>().getString(R.string.free_radio),
            image = "https://www.eztanda.com/images/eztanda_logo.png",
            playerFramework = get(),
        )
    }

}