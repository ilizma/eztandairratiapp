package com.ilizma.menu.flow.navigator

import com.ilizma.menu.flow.model.COUNTRY_CODE
import com.ilizma.menu.flow.model.PHONE_NUMBER
import com.ilizma.menu.flow.model.WHATSAPP_APP_LINK
import com.ilizma.menu.flow.model.WHATSAPP_APP_STORE_BROWSER_LINK
import com.ilizma.menu.flow.model.WHATSAPP_APP_STORE_LINK
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class WhatsAppNavigatorImp : WhatsAppNavigator {

    override fun navigate() {
        "$COUNTRY_CODE$PHONE_NUMBER"
            .let { "$WHATSAPP_APP_LINK$it" }
            .let { NSURL(string = it) }
            .let { url ->
                if (UIApplication.sharedApplication.canOpenURL(url)) {
                    UIApplication.sharedApplication.openURL(
                        url = url,
                        options = mapOf<Any?, Any?>(),
                        completionHandler = {
                            if (!it) {
                                openStore()
                            }
                        },
                    )
                } else {
                    // WhatsApp not installed
                    openStore()
                }
            }
    }

    private fun openStore() {
        WHATSAPP_APP_STORE_LINK
            .let { NSURL(string = it) }
            .let { url ->
                if (UIApplication.sharedApplication.canOpenURL(url)) {
                    UIApplication.sharedApplication.openURL(
                        url = url,
                        options = mapOf<Any?, Any?>(),
                        completionHandler = {
                            if (!it) {
                                // App Store not available (rare), open browser
                                openBrowser()
                            }
                        }
                    )
                } else {
                    // App Store not available, open browser
                    openBrowser()
                }
            }
    }

    private fun openBrowser() {
        WHATSAPP_APP_STORE_BROWSER_LINK
            .let { NSURL(string = it) }
            .let { url ->
                if (UIApplication.sharedApplication.canOpenURL(url)) {
                    UIApplication.sharedApplication.openURL(
                        url = url,
                        options = mapOf<Any?, Any?>(),
                        completionHandler = {
                            if (!it) {
                                println("Failed to open all WhatsApp installation options")
                            }
                        }
                    )
                } else {
                    println("Failed to open all WhatsApp installation options")
                }
            }
    }
}