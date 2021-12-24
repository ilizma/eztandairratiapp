package com.ilizma.player.framework.model

sealed class PlayerState {

    object Playing : PlayerState()

    object Loading : PlayerState()

    object Stopped : PlayerState()

    object Error : PlayerState()

}