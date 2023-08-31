package com.ilizma.player.domain.model

sealed class PlayerState {

    data object Playing : PlayerState()

    data object Loading : PlayerState()

    data object Stopped : PlayerState()

    sealed class Error : PlayerState() {

        data object Malformed : Error()

        data object Unsupported : Error()

        data object Timeout : Error()

        data object Network : Error()

        data object MediaDisconnected : Error()

        data object Unknown : Error()

        data object GenericError : Error()

    }

}