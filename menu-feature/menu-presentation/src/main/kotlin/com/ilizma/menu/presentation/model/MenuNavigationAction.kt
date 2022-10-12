package com.ilizma.menu.presentation.model

sealed class MenuNavigationAction {

    object Instagram : MenuNavigationAction()

    object Twitter : MenuNavigationAction()

    object Facebook : MenuNavigationAction()

    object Phone : MenuNavigationAction()

    object Web : MenuNavigationAction()

    object Back : MenuNavigationAction()

}
