package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri

class PhoneNavigatorImp(
    private val context: Context,
) : PhoneNavigator {

    override fun navigate() {
        "948563766"
            .let { Uri.parse("tel:$it") }
            .let { context.startActivity(Intent(Intent.ACTION_DIAL, it)) }
        // TODO: 8/12/21 unify whatsapp and phone
    }

}