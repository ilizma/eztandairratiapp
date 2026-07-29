package com.ilizma.main.view.widget.receiver

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import com.ilizma.main.view.widget.PlayerWidget
import com.ilizma.main.view.widget.mapper.PlayerStateMapper
import com.ilizma.main.view.widget.model.PlayerStateKeys
import com.ilizma.main.view.widget.model.json
import com.ilizma.player.domain.usecase.PlayerStateUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin
import com.ilizma.main.view.widget.model.PlayerState as WidgetPlayerState

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
            try {
                // Obtener el estado real actual antes de actualizar los widgets
                val playerStateUseCase: PlayerStateUseCase = getKoin().get()
                val currentState = playerStateUseCase().first()
                val widgetState = PlayerStateMapper().from(currentState)

                appWidgetIds.forEach { appWidgetId ->
                    val glanceId = GlanceAppWidgetManager(context)
                        .getGlanceIdBy(appWidgetId)

                    updateAppWidgetState(context, glanceId) { prefs ->
                        prefs[PlayerStateKeys.state] = json.encodeToString(
                            serializer = WidgetPlayerState.serializer(),
                            value = widgetState
                        )
                    }

                    glanceAppWidget.update(context, glanceId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
