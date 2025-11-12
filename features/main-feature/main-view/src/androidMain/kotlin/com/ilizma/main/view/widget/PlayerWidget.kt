package com.ilizma.main.view.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.LocalContext
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.ilizma.main.view.widget.callback.PlayerCallback
import com.ilizma.main.view.widget.model.PlayerState
import com.ilizma.main.view.widget.model.PlayerStateKeys
import com.ilizma.main.view.widget.model.PlayerStateKeys.actionKey
import com.ilizma.main.view.widget.model.WidgetAction
import com.ilizma.main.view.widget.model.json
import com.ilizma.main.view.widget.utils.PlayerWidgetPreviewProvider
import com.ilizma.resources.R
import com.ilizma.resources.ui.theme.PurpleDay
import com.ilizma.resources.ui.theme.White
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

class PlayerWidget() : GlanceAppWidget() {

    override suspend fun provideGlance(
        context: Context,
        id: GlanceId,
    ) {
        provideContent {
            val state = currentState<Preferences>()
            Content(
                state = state
            )
        }
    }

    @Composable
    @GlanceComposable
    private fun Content(
        state: Preferences,
    ) {
        val context = LocalContext.current
        val playerState = state[PlayerStateKeys.state]
            ?.let { json.decodeFromString<PlayerState>(string = it) }
            ?: PlayerState.Stopped

        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(4.dp)
                .background(ColorProvider(color = PurpleDay)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = context.getString(R.string.app_name),
                modifier = GlanceModifier.padding(horizontal = 8.dp),
                style = TextStyle(
                    color = ColorProvider(color = White),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(GlanceModifier.height(12.dp))

            Image(
                modifier = GlanceModifier
                    .padding(4.dp)
                    .size(92.dp),
                provider = ImageProvider(resId = R.drawable.img_splash),
                colorFilter = ColorFilter.tint(ColorProvider(color = White)),
                contentDescription = "Eztanda logo",
            )

            Spacer(GlanceModifier.height(24.dp))

            Box(
                modifier = GlanceModifier
                    .padding(4.dp)
                    .background(ColorProvider(color = White))
                    .cornerRadius(24.dp),
                contentAlignment = Alignment.Center
            ) {
                when (playerState) {
                    PlayerState.Loading -> CircularProgressIndicator(
                        modifier = GlanceModifier.size(28.dp),
                        color = ColorProvider(color = PurpleDay),
                    )

                    else -> Image(
                        modifier = GlanceModifier
                            .size(26.dp)
                            .clickable(
                                actionRunCallback<PlayerCallback>(
                                    parameters = actionParametersOf(
                                        actionKey to if (playerState == PlayerState.Playing) {
                                            WidgetAction.STOP.name
                                        } else {
                                            WidgetAction.PLAY.name
                                        }
                                    )
                                )
                            ),
                        provider = ImageProvider(
                            resId = if (playerState == PlayerState.Playing) {
                                R.drawable.ic_stop
                            } else {
                                R.drawable.ic_play_arrow
                            }
                        ),
                        colorFilter = ColorFilter.tint(ColorProvider(color = PurpleDay)),
                        contentDescription = if (playerState == PlayerState.Playing) {
                            "Stop"
                        } else {
                            "Play"
                        },
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun Preview(
        @PreviewParameter(PlayerWidgetPreviewProvider::class) state: PlayerState,
    ) {
        val stateJson = json.encodeToString(PlayerState.serializer(), state)
        val samplePreferences = preferencesOf(PlayerStateKeys.state to stateJson)
        Content(state = samplePreferences)
    }

}
