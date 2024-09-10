package com.ilizma.cast.framework.listener

import com.google.android.gms.cast.framework.CastState.*
import com.google.android.gms.cast.framework.CastStateListener
import com.ilizma.cast.framework.model.CastState

class CastStateListenerImp : CastStateListener {

    private lateinit var state: (CastState) -> Unit

    fun init(
        state: (CastState) -> Unit,
    ) {
        this.state = state
    }

    override fun onCastStateChanged(newState: Int) {
        when (newState) {
            NO_DEVICES_AVAILABLE, NOT_CONNECTED -> state(CastState.DISCONNECTED)
            CONNECTING, CONNECTED -> state(CastState.CONNECTED)
        }
    }

}