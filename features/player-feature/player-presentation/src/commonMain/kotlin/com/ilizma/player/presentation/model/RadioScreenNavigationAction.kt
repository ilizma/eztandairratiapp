package com.ilizma.player.presentation.model

sealed interface RadioScreenNavigationAction {

    data object Back : RadioScreenNavigationAction

    data object CastPlayer : RadioScreenNavigationAction

}
