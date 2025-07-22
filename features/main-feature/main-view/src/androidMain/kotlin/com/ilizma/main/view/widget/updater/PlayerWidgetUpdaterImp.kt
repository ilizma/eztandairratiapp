package com.ilizma.main.view.widget.updater

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import com.ilizma.main.view.widget.PlayerWidget
import com.ilizma.main.view.widget.mapper.PlayerStateMapper
import com.ilizma.main.view.widget.model.PlayerStateKeys
import com.ilizma.main.view.widget.model.json
import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.updater.PlayerWidgetUpdater
import kotlin.jvm.java

class PlayerWidgetUpdaterImp : PlayerWidgetUpdater {

    override suspend fun updateMediaWidget(
        context: Context,
        playerState: PlayerState,
    ) {
        val widgetState = PlayerStateMapper().from(playerState)

        val glanceId = GlanceAppWidgetManager(context)
            .getGlanceIds(PlayerWidget::class.java)
            .firstOrNull() ?: return

        updateAppWidgetState(
            context = context,
            glanceId = glanceId
        ) { prefs ->
            prefs[PlayerStateKeys.state] = json.encodeToString(value = widgetState)
        }

        PlayerWidget().update(
            context = context,
            id = glanceId,
        )
    }

}