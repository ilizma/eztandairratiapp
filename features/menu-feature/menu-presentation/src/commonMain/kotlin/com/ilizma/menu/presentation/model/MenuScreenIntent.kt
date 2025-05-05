package com.ilizma.menu.presentation.model

sealed interface MenuScreenIntent {
    object Instagram : MenuScreenIntent
    object Twitter : MenuScreenIntent
    object Facebook : MenuScreenIntent
    object Phone : MenuScreenIntent
    object WhatsApp : MenuScreenIntent
    object Web : MenuScreenIntent
    object Back : MenuScreenIntent
}
