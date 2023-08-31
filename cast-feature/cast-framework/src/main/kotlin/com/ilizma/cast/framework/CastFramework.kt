package com.ilizma.cast.framework

import com.ilizma.cast.framework.model.CastState
import io.reactivex.rxjava3.core.Observable

interface CastFramework {

    fun init()

    val castState: Observable<CastState>

    fun <T> setUpMediaRouteButton(
        menu: T,
        menuResourceId: Int,
    )

    fun onResume()

    fun onDestroy()

}