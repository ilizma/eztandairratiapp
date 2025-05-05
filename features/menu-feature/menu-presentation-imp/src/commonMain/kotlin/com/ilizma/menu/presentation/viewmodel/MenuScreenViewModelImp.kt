package com.ilizma.menu.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.model.MenuNavigationAction.Back
import com.ilizma.menu.presentation.model.MenuNavigationAction.Facebook
import com.ilizma.menu.presentation.model.MenuNavigationAction.Instagram
import com.ilizma.menu.presentation.model.MenuNavigationAction.Phone
import com.ilizma.menu.presentation.model.MenuNavigationAction.Twitter
import com.ilizma.menu.presentation.model.MenuNavigationAction.Web
import com.ilizma.menu.presentation.model.MenuNavigationAction.WhatsApp
import com.ilizma.menu.presentation.model.MenuScreenIntent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MenuScreenViewModelImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val _navigationAction: MutableSharedFlow<MenuNavigationAction>,
) : MenuScreenViewModel() {

    override val navigationAction: Flow<MenuNavigationAction> = _navigationAction

    override fun onIntent(
        intent: MenuScreenIntent,
    ) {
        when (intent) {
            MenuScreenIntent.Instagram -> onInstagram()
            MenuScreenIntent.Twitter -> onTwitter()
            MenuScreenIntent.Facebook -> onFacebook()
            MenuScreenIntent.Phone -> onPhone()
            MenuScreenIntent.WhatsApp -> onWhatsApp()
            MenuScreenIntent.Web -> onWeb()
            MenuScreenIntent.Back -> onBack()
        }
    }

    private fun onInstagram() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Instagram) }
    }

    private fun onTwitter() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Twitter) }
    }

    private fun onFacebook() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Facebook) }
    }

    private fun onPhone() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Phone) }
    }

    private fun onWhatsApp() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(WhatsApp) }
    }

    private fun onWeb() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Web) }
    }

    private fun onBack() {
        viewModelScope.launch(dispatcher) { _navigationAction.emit(Back) }
    }

}
