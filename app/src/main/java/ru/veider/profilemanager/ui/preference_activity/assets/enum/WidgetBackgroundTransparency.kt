package ru.veider.profilemanager.ui.preference_activity.assets.enum

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R

data class WidgetBackgroundTransparency(
    val value: Int
)
val WidgetBackgroundTransparency.desc: String
    @Composable
    get() = when (value) {
        0      -> stringResource(id = R.string.widget_transparency_none)
        in 1..99 -> "${stringResource(id = R.string.widget_transparency_middle)} $value%"
        else   -> stringResource(id = R.string.widget_transparency_full)
    }