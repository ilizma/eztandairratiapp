package com.ilizma.player.framework.model

sealed class PlayerConnectionState {

    object Connected  : PlayerConnectionState()

    object Disconnected  : PlayerConnectionState()

}