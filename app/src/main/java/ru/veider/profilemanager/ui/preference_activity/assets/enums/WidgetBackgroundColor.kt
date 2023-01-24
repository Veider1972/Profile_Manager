package ru.veider.profilemanager.ui.preference_activity.assets.enums

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R

enum class WidgetBackgroundColor {
    WHITE, GREY, BLACK, NONE;
    val desc
        @Composable
        get() =
            when (this) {
                WHITE -> stringResource(id = R.string.widget_background_color_white)
                GREY  -> stringResource(id = R.string.widget_background_color_grey)
                BLACK -> stringResource(id = R.string.widget_background_color_black)
                NONE  -> stringResource(id = R.string.widget_background_color_none)
            }
}

val Int.asWidgetBackgroundColor
    get() =
        when (this) {
            WidgetBackgroundColor.WHITE.ordinal -> WidgetBackgroundColor.WHITE
            WidgetBackgroundColor.GREY.ordinal  -> WidgetBackgroundColor.GREY
            WidgetBackgroundColor.BLACK.ordinal -> WidgetBackgroundColor.BLACK
            else                                -> WidgetBackgroundColor.NONE
        }
