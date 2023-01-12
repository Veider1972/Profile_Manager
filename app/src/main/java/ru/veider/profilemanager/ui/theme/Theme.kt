package ru.veider.profilemanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette
    @Composable
    get() =
        darkColors(
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
        darkColors(
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
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}