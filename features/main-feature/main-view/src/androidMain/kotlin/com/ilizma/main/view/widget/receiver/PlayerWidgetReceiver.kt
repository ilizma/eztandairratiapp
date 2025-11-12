package com.ilizma.main.view.widget.receiver

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import com.ilizma.main.view.widget.PlayerWidget
import com.ilizma.main.view.widget.model.PlayerState
import com.ilizma.main.view.widget.model.PlayerStateKeys
import com.ilizma.main.view.widget.model.json
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PlayerWidgetReceiver: GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = PlayerWidget()

    private val coroutineScope = MainScope()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        coroutineScope.launch {
            appWidgetIds.forEach { appWidgetId ->
                try {
                    val glanceId = GlanceAppWidgetManager(context)
                        .getGlanceIdBy(appWidgetId)

                    updateAppWidgetState(context, glanceId) { prefs ->
                        if (prefs[PlayerStateKeys.state] == null) {
                            prefs[PlayerStateKeys.state] = json.encodeToString(
                                serializer = PlayerState.serializer(),
                                value = PlayerState.Stopped
                            )
                        }
                    }

                    glanceAppWidget.update(context, glanceId)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}