package com.ilizma.player.presentation.model

sealed interface RadioScreenIntent {

    data object Play : RadioScreenIntent
    data object Stop : RadioScreenIntent
    data object Back : RadioScreenIntent

}
