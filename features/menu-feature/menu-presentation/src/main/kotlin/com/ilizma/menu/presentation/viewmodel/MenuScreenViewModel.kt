package com.ilizma.menu.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.menu.presentation.model.MenuNavigationAction
import kotlinx.coroutines.flow.Flow

abstract class MenuScreenViewModel : ViewModel() {

    abstract val navigationAction: Flow<MenuNavigationAction>

    abstract fun onInstagram()

    abstract fun onTwitter()

    abstract fun onFacebook()

    abstract fun onPhone()

    abstract fun onWeb()

    abstract fun onBack()

}