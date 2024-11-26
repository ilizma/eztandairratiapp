package com.ilizma.menu.flow.navigator

import androidx.compose.ui.platform.UriHandler
import com.ilizma.menu.flow.model.INSTAGRAM_URL

class InstagramNavigatorImp : InstagramNavigator {

    override fun navigate(
        uriHandler: UriHandler,
    ) {
        INSTAGRAM_URL
            .let { uriHandler.openUri(it) }
    }

}