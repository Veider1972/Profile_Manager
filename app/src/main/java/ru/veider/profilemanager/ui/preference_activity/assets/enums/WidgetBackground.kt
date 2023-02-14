package ru.veider.profilemanager.ui.preference_activity.assets.enums

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R

enum class WidgetBackground {
    WHITE, GRAY, BLACK, NONE;

    val desc
        @Composable
        get() =
            when (this) {
                WHITE -> stringResource(id = R.string.widget_background_color_white)
                GRAY  -> stringResource(id = R.string.widget_background_color_grey)
                BLACK -> stringResource(id = R.string.widget_background_color_black)
                NONE  -> stringResource(id = R.string.widget_background_color_none)
            }
    val resId
        get() =
            when (this) {
                WHITE -> R.drawable.widget_background_white
                GRAY  -> R.drawable.widget_background_gray
                BLACK -> R.drawable.widget_background_black
                NONE  -> R.drawable.widget_background_none
            }
}

val Int.asWidgetBackground
    get() =
        when (this) {
            WidgetBackground.WHITE.ordinal -> WidgetBackground.WHITE
            WidgetBackground.GRAY.ordinal  -> WidgetBackground.GRAY
            WidgetBackground.BLACK.ordinal -> WidgetBackground.BLACK
            else                           -> WidgetBackground.NONE
        }
