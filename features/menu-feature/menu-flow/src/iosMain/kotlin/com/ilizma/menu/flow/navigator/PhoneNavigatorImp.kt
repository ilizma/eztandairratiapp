package com.ilizma.menu.flow.navigator

import com.ilizma.menu.flow.model.PHONE_NUMBER
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class PhoneNavigatorImp : PhoneNavigator {

    override fun navigate() {
        PHONE_NUMBER
            .let { "tel://$it" }
            .let { NSURL(string = it) }
            .let { url ->
                if (UIApplication.sharedApplication.canOpenURL(url)) {
                    UIApplication.sharedApplication.openURL(
                        url = url,
                        options = mapOf<Any?, Any?>(),
                        completionHandler = {
                            if (!it) {
                                println("Failed to open URL: $url")
                            }
                        },
                    )
                } else {
                    println("Failed to open URL: $url")
                }
            }
    }

}