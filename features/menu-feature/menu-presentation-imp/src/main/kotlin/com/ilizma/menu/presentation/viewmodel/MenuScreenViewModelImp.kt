package com.ilizma.menu.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.model.MenuNavigationAction.Back
import com.ilizma.menu.presentation.model.MenuNavigationAction.Facebook
import com.ilizma.menu.presentation.model.MenuNavigationAction.Instagram
import com.ilizma.menu.presentation.model.MenuNavigationAction.Phone
import com.ilizma.menu.presentation.model.MenuNavigationAction.Twitter
import com.ilizma.menu.presentation.model.MenuNavigationAction.Web
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MenuScreenViewModelImp(
    private val _navigationAction: MutableSharedFlow<MenuNavigationAction>,
) : MenuScreenViewModel() {

    override val navigationAction: Flow<MenuNavigationAction> = _navigationAction

    override fun onInstagram() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Instagram) }
    }

    override fun onTwitter() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Twitter) }
    }

    override fun onFacebook() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Facebook) }
    }

    override fun onPhone() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Phone) }
    }

    override fun onWeb() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Web) }
    }

    override fun onBack() {
        viewModelScope.launch(Dispatchers.IO) { _navigationAction.emit(Back) }
    }

}
