package com.ilizma.menu.flow.navigator

import androidx.compose.ui.platform.UriHandler
import com.ilizma.menu.flow.model.FACEBOOK_URL

class FacebookNavigatorImp : FacebookNavigator {

    override fun navigate(
        uriHandler: UriHandler,
    ) {
        FACEBOOK_URL
            .let { uriHandler.openUri(it) }
    }

}