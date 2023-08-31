package com.ilizma.player.presentation.model

sealed class RadioScreenNavigationAction {

    data object Back : RadioScreenNavigationAction()

    data object CastPlayer : RadioScreenNavigationAction()

}
