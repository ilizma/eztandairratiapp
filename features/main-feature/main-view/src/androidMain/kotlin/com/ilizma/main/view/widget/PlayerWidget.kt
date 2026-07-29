package com.ilizma.main.view.widget

import android.content.Context
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.material3.ColorProviders
import com.ilizma.main.view.widget.callback.PlayerCallback
import com.ilizma.main.view.widget.model.PlayerState
import com.ilizma.main.view.widget.model.PlayerStateKeys
import com.ilizma.main.view.widget.model.PlayerStateKeys.actionKey
import com.ilizma.main.view.widget.model.WidgetAction
import com.ilizma.main.view.widget.model.json
import com.ilizma.main.view.widget.utils.PlayerWidgetPreviewProvider
import com.ilizma.resources.R
import com.ilizma.resources.ui.theme.PurpleDay
import com.ilizma.resources.ui.theme.PurpleNight
import com.ilizma.resources.ui.theme.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

class PlayerWidget : GlanceAppWidget() {

    companion object {
        private val SMALL_SQUARE = DpSize(100.dp, 140.dp)
        private val HORIZONTAL_RECT = DpSize(220.dp, 140.dp)
        private val BIG_SQUARE = DpSize(220.dp, 220.dp)

        private val WidgetColors = ColorProviders(
            light = lightColorScheme(
                primary = White,
                onPrimary = PurpleDay,
                primaryContainer = PurpleDay,
                onPrimaryContainer = White,
            ),
            dark = darkColorScheme(
                primary = White,
                onPrimary = PurpleNight,
                primaryContainer = PurpleNight,
                onPrimaryContainer = White,
            )
        )
    }

    override val sizeMode = SizeMode.Responsive(
        setOf(SMALL_SQUARE, HORIZONTAL_RECT, BIG_SQUARE),
    )

    override suspend fun provideGlance(
        context: Context,
        id: GlanceId,
    ) {
        provideContent {
            val state = currentState<Preferences>()
            GlanceTheme(colors = WidgetColors) {
                Content(state = state)
            }
        }
    }

    @Composable
    @GlanceComposable
    private fun Content(
        state: Preferences,
    ) {
        val size = LocalSize.current
        val context = LocalContext.current
        val playerState = state[PlayerStateKeys.state]
            ?.let { json.decodeFromString<PlayerState>(string = it) }
            ?: PlayerState.Stopped

        // Cambiamos a lógica de Aspect Ratio (si el ancho es mayor que el alto, es horizontal)
        val isHorizontal = size.width > size.height

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .appWidgetBackground()
                .background(GlanceTheme.colors.primaryContainer)
                .cornerRadius(28.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isHorizontal) {
                HorizontalLayout(playerState, size)
            } else {
                VerticalLayout(context, playerState, size)
            }
        }
    }

    @Composable
    private fun VerticalLayout(
        context: Context,
        playerState: PlayerState,
        size: DpSize,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically,
            modifier = GlanceModifier.fillMaxSize()
        ) {

            // Escalado de logo más agresivo
            val imageSize = when {
                size.height < 160.dp -> 100.dp
                size.height < 200.dp -> 130.dp
                else -> 160.dp
            }

            Image(
                modifier = GlanceModifier.size(imageSize),
                provider = ImageProvider(resId = R.drawable.img_splash),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                contentDescription = "Eztanda logo",
            )

            Spacer(GlanceModifier.height(12.dp))

            PlayerButton(playerState)
        }
    }

    @Composable
    private fun HorizontalLayout(
        playerState: PlayerState,
        size: DpSize,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = GlanceModifier.fillMaxSize()
        ) {
            // En horizontal quitamos el texto y agrandamos el logo según el ancho
            val imageSize = if (size.width < 250.dp) 110.dp else 140.dp
            Image(
                modifier = GlanceModifier.size(imageSize),
                provider = ImageProvider(resId = R.drawable.img_splash),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                contentDescription = "Eztanda logo",
            )

            Spacer(GlanceModifier.width(if (size.width < 250.dp) 24.dp else 48.dp))

            PlayerButton(playerState)
        }
    }

    @Composable
    private fun PlayerButton(playerState: PlayerState) {
        Box(
            modifier = GlanceModifier
                .size(48.dp)
                .background(GlanceTheme.colors.primary)
                .cornerRadius(24.dp)
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
            contentAlignment = Alignment.Center
        ) {
            if (playerState == PlayerState.Loading) {
                CircularProgressIndicator(
                    modifier = GlanceModifier.size(32.dp),
                    color = GlanceTheme.colors.onPrimary
                )
            } else {
                Image(
                    modifier = GlanceModifier.size(28.dp),
                    provider = ImageProvider(
                        resId = if (playerState == PlayerState.Playing) {
                            R.drawable.ic_stop
                        } else {
                            R.drawable.ic_play_arrow
                        }
                    ),
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimary),
                    contentDescription = if (playerState == PlayerState.Playing) "Stop" else "Play",
                )
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
        GlanceTheme(colors = WidgetColors) {
            Content(state = samplePreferences)
        }
    }

}
