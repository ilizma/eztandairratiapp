package com.ilizma.cast.framework

import com.ilizma.cast.framework.model.CastState
import kotlinx.coroutines.flow.Flow

interface CastFramework {

    fun init()

    val castState: Flow<CastState>

    fun <T> setUpMediaRouteButton(
        menu: T,
        menuResourceId: Int,
    )

    fun onResume()

    fun onDestroy()

}