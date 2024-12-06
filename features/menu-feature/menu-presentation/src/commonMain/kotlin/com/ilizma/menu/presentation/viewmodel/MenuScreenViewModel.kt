package com.ilizma.menu.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.model.MenuScreenIntent
import kotlinx.coroutines.flow.Flow

abstract class MenuScreenViewModel : ViewModel() {

    abstract val navigationAction: Flow<MenuNavigationAction>

    abstract fun onIntent(intent: MenuScreenIntent)

}