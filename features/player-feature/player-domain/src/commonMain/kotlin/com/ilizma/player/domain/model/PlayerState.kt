package com.ilizma.player.domain.model

sealed interface PlayerState {

    data object Playing : PlayerState

    data object Loading : PlayerState

    data object Stopped : PlayerState

    sealed interface Error : PlayerState {

        data object Malformed : Error

        data object Unsupported : Error

        data object Timeout : Error

        data object Network : Error

        data object MediaDisconnected : Error

        data object Unknown : Error

        data object GenericError : Error

    }

}