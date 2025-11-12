package com.ilizma.main.view.widget.updater

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import com.ilizma.main.view.widget.PlayerWidget
import com.ilizma.main.view.widget.mapper.PlayerStateMapper
import com.ilizma.main.view.widget.model.PlayerStateKeys
import com.ilizma.main.view.widget.model.PlayerState
import com.ilizma.main.view.widget.model.json
import com.ilizma.player.framework.model.PlayerState as FrameworkPlayerState
import com.ilizma.player.framework.updater.PlayerWidgetUpdater

class PlayerWidgetUpdaterImp : PlayerWidgetUpdater {

    override suspend fun updateMediaWidget(
        context: Context,
        playerState: FrameworkPlayerState,
    ) {
        try {
            val widgetState = PlayerStateMapper().from(playerState)

            val glanceIds = GlanceAppWidgetManager(context)
                .getGlanceIds(PlayerWidget::class.java)

            if (glanceIds.isEmpty()) {
                return
            }

            glanceIds.forEach { glanceId ->
                updateAppWidgetState(
                    context = context,
                    glanceId = glanceId
                ) { prefs ->
                    prefs[PlayerStateKeys.state] = json.encodeToString(
                        PlayerState.serializer(),
                        widgetState
                    )
                }

                PlayerWidget().update(
                    context = context,
                    id = glanceId,
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}