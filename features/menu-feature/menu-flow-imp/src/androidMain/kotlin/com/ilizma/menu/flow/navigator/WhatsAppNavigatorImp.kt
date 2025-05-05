package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.ilizma.menu.flow.model.COUNTRY_CODE
import com.ilizma.menu.flow.model.PHONE_NUMBER
import com.ilizma.menu.flow.model.WHATSAPP_APP_LINK
import com.ilizma.menu.flow.model.WHATSAPP_PLAY_STORE_BROWSER_LINK
import com.ilizma.menu.flow.model.WHATSAPP_PLAY_STORE_LINK

class WhatsAppNavigatorImp(
    private val context: Context,
) : WhatsAppNavigator {

    override fun navigate() {
        try {
            openWhatsapp()
        } catch (_: Exception) {
            // WhatsApp not installed
            try {
                openStore()
            } catch (_: Exception) {
                // Play Store not available, open browser
                openBrowser()
            }
        }
    }

    private fun openWhatsapp() {
        "$COUNTRY_CODE$PHONE_NUMBER"
            .let { "$WHATSAPP_APP_LINK$it" }
            .toUri()
            .let { Intent(Intent.ACTION_VIEW, it) }
            .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
            .let { context.startActivity(it) }
    }

    private fun openStore() {
        WHATSAPP_PLAY_STORE_LINK
            .toUri()
            .let { Intent(Intent.ACTION_VIEW, it) }
            .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
            .let { context.startActivity(it) }
    }

    private fun openBrowser() {
        WHATSAPP_PLAY_STORE_BROWSER_LINK
            .toUri()
            .let { Intent(Intent.ACTION_VIEW, it) }
            .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
            .let { context.startActivity(it) }
    }

}