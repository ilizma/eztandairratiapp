package com.ilizma.player.data.model

sealed class PlayerState {

    object Playing : PlayerState()

    object Loading : PlayerState()

    object Stopped : PlayerState()

    sealed class Error : PlayerState() {

        object Malformed : Error()

        object Unsupported : Error()

        object Timeout : Error()

        object Network : Error()

        object MediaDisconnected : Error()

        object Unknown : Error()

        object GenericError : Error()

    }

}