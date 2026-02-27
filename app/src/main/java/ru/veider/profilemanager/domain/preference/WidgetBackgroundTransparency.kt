package ru.veider.profilemanager.domain.preference

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R

data class WidgetBackgroundTransparency(
    val value: Int
){
    val desc
        @Composable
        get() = when (value) {
            0        -> stringResource(id = R.string.widget_transparency_full)
            in 1..99 -> "${stringResource(id = R.string.widget_transparency_middle)} ${100-value}%"
            else     -> stringResource(id = R.string.widget_transparency_none)
        }
}

