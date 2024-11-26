package com.ilizma.menu.flow.navigator

import androidx.compose.ui.platform.UriHandler
import com.ilizma.menu.flow.model.TWITTER_URL

class TwitterNavigatorImp : TwitterNavigator {

    override fun navigate(
        uriHandler: UriHandler,
    ) {
        TWITTER_URL
            .let { uriHandler.openUri(it) }
    }

}