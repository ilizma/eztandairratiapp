package com.ilizma.player.data.model

sealed class PlayerState {

    object Playing : PlayerState()

    object Loading : PlayerState()

    object Stopped : PlayerState()

    object Error : PlayerState()

}