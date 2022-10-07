package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri

class WhatsappNavigatorImp(
    private val context: Context,
) : WhatsappNavigator {

    override fun navigate() {
        "633489072"
            .let { Uri.parse("tel:$it") }
            .let { context.startActivity(Intent(Intent.ACTION_DIAL, it)) }
        // TODO: 8/12/21 unify whatsapp and phone
    }

}