package com.ilizma.resources.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Black,
    secondary = SecondaryNight,
    tertiary = PurpleNight,
    background = BackgroundNight,
    surface = Black,
    onPrimary = White,
)

private val LightColorScheme = lightColorScheme(
    primary = White,
    secondary = SecondaryDay,
    tertiary = PurpleDay,
    background = BackgroundDay,
    surface = White,
    onPrimary = Black,

    /* Other default colors to override
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
actual fun EztandaIrratiappTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}