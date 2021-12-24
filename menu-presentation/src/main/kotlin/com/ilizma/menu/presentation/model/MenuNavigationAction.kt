package com.ilizma.menu.presentation.model

sealed class MenuNavigationAction {

    object Twitter : MenuNavigationAction()

    object Facebook : MenuNavigationAction()

    object Whatsapp : MenuNavigationAction()

    object Phone : MenuNavigationAction()

    object Web : MenuNavigationAction()

}
