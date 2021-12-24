package com.ilizma.menu.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilizma.menu.presentation.model.MenuNavigationAction

abstract class MenuViewModel : ViewModel() {

    abstract val navigationAction: LiveData<MenuNavigationAction>

    abstract fun onTwitter()

    abstract fun onFacebook()

    abstract fun onWhatsapp()

    abstract fun onPhone()

    abstract fun onWeb()

}