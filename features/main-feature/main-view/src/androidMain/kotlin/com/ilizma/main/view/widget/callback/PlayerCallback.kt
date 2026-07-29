package com.ilizma.main.view.widget.callback

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import com.ilizma.main.view.widget.PlayerWidget
import com.ilizma.main.view.widget.model.PlayerState
import com.ilizma.main.view.widget.model.PlayerStateKeys
import com.ilizma.main.view.widget.model.PlayerStateKeys.actionKey
import com.ilizma.main.view.widget.model.WidgetAction
import com.ilizma.main.view.widget.model.json
import com.ilizma.player.framework.service.MusicService
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

        updateAppWidgetState(
            context = context,
            glanceId = glanceId
        ) { prefs ->
            val newState = when (action) {
                WidgetAction.PLAY.name -> PlayerState.Loading
                WidgetAction.STOP.name -> PlayerState.Stopped
                else -> return@updateAppWidgetState
            }
            prefs[PlayerStateKeys.state] = json.encodeToString(
                serializer = PlayerState.serializer(),
                value = newState
            )
        }

        PlayerWidget().update(
            context = context,
            id = glanceId,
        )

        val intent = Intent(context, MusicService::class.java).apply {
            putExtra(WIDGET_ACTION, action)
        }

        try {
            context.startForegroundService(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}