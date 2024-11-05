package com.ilizma.cast.framework.di

import android.app.Activity
import android.content.res.Resources
import com.ilizma.cast.framework.CastFramework
import com.ilizma.cast.framework.CastFrameworkImp
import com.ilizma.cast.framework.listener.CastStateListenerImp
import com.ilizma.cast.framework.listener.SessionManagerListenerImp
import com.ilizma.cast.framework.model.CastState.DISCONNECTED
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.resources.R
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.module.Module
import org.koin.dsl.module

val castFrameworkModule: Module = module {

    scope<MainActivity> {
        scoped<CastFramework> {
            CastFrameworkImp(
                activity = get(),
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

}