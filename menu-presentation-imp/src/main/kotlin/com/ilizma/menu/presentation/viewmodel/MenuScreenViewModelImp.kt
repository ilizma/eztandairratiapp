package com.ilizma.menu.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.model.MenuNavigationAction.*
import com.ilizma.presentation.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class MenuScreenViewModelImp @AssistedInject constructor(
    @Assisted private val _navigationAction: SingleLiveEvent<MenuNavigationAction>,
) : MenuScreenViewModel() {

    override val navigationAction: LiveData<MenuNavigationAction> = _navigationAction

    override fun onTwitter() {
        _navigationAction.postValue(Twitter)
    }

    override fun onFacebook() {
        _navigationAction.postValue(Facebook)
    }

    override fun onWhatsapp() {
        _navigationAction.postValue(Whatsapp)
    }

    override fun onPhone() {
        _navigationAction.postValue(Phone)
    }

    override fun onWeb() {
        _navigationAction.postValue(Web)
    }

}
