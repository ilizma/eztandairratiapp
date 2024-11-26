package com.ilizma.menu.flow.navigator

import androidx.compose.ui.platform.UriHandler
import com.ilizma.menu.flow.model.WEB_URL

class WebNavigatorImp : WebNavigator {

    override fun navigate(
        uriHandler: UriHandler,
    ) {
        WEB_URL
            .let { uriHandler.openUri(it) }
    }

}