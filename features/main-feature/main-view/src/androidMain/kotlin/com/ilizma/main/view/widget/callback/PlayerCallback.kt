package com.ilizma.main.view.widget.callback

import android.content.Context
import android.content.Intent
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.ilizma.main.view.widget.model.PlayerStateKeys.actionKey
import com.ilizma.player.framework.service.MusicService
import kotlin.jvm.java
import androidx.media3.common.util.UnstableApi
import com.ilizma.player.framework.service.WIDGET_ACTION

class PlayerCallback : ActionCallback {

    @UnstableApi
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val action = parameters[actionKey] ?: return

        val intent = Intent(context, MusicService::class.java).apply {
            putExtra(WIDGET_ACTION, action)
        }
        context.startService(intent)
    }

}