package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import com.ilizma.menu.flow.model.PHONE_NUMBER
import androidx.core.net.toUri

class PhoneNavigatorImp(
    private val context: Context,
) : PhoneNavigator {

    override fun navigate() {
        PHONE_NUMBER
            .let { "tel:$it" }
            .toUri()
            .let { Intent(Intent.ACTION_DIAL, it) }
            .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
            .let { context.startActivity(it) }
    }

}