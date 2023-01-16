package ru.veider.profilemanager.ui.preference_activity.assets.enum

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R

enum class WidgetBackgroundColor(val value: Int) {
    WHITE(0), GREY(1), BLACK(2), NONE(3)
}

val WidgetBackgroundColor.desc: String
    @Composable
    get() =
        when (this) {
            WidgetBackgroundColor.WHITE -> stringResource(id = R.string.widget_background_color_white)
            WidgetBackgroundColor.GREY  -> stringResource(id = R.string.widget_background_color_grey)
            WidgetBackgroundColor.BLACK -> stringResource(id = R.string.widget_background_color_black)
            WidgetBackgroundColor.NONE  -> stringResource(id = R.string.widget_background_color_none)
        }