package ru.veider.profilemanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

private val DarkColorPalette
    @Composable
    get() =
        darkColorScheme(
            primary = colorPrimary,
            secondary = colorPrimaryDark,
            background = colorOnSurface,
            surface = colorSurface,
            onSurface = colorOnSurface,
            error = colorError,
            onPrimary = colorOnPrimary,
            onSecondary = colorOnSecondary
        )

private val LightColorPalette
    @Composable
    get() =
        lightColorScheme(
            primary = colorPrimary,
            secondary = colorPrimaryDark,
            background = colorOnSurface,
            surface = colorSurface,
            onSurface = colorOnSurface,
            error = colorError,
            onPrimary = colorOnPrimary,
            onSecondary = colorOnSecondary
        )

@Composable
fun ProfileManagerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}