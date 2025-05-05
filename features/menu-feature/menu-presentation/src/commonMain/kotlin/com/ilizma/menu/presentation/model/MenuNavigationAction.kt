package com.ilizma.menu.presentation.model

sealed interface MenuNavigationAction {
    data object Instagram : MenuNavigationAction
    data object Twitter : MenuNavigationAction
    data object Facebook : MenuNavigationAction
    data object Phone : MenuNavigationAction
    data object WhatsApp : MenuNavigationAction
    data object Web : MenuNavigationAction
    data object Back : MenuNavigationAction
}
