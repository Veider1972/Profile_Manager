package ru.veider.profilemanager.domain.preference

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.*

enum class WidgetColor {
    BLUE, GREEN, YELLOW, ORANGE, RED, PURPLE;
    val desc
        @Composable
        get() =
            when (this) {
                BLUE   -> stringResource(id = R.string.profile_widget_symbol_color_blue)
                ORANGE -> stringResource(id = R.string.profile_widget_symbol_color_orange)
                PURPLE -> stringResource(id = R.string.profile_widget_symbol_color_purple)
                RED    -> stringResource(id = R.string.profile_widget_symbol_color_red)
                YELLOW -> stringResource(id = R.string.profile_widget_symbol_color_yellow)
                GREEN  -> stringResource(id = R.string.profile_widget_symbol_color_green)
            }

    val color
        @Composable
        get() =
            when (this) {
                BLUE   -> colorBlue
                ORANGE -> colorOrange
                PURPLE -> colorPurple
                RED    -> colorRed
                YELLOW -> colorYellow
                GREEN  -> colorGreen
            }
    val ringId
        get() =
            when (this) {
                BLUE   -> R.drawable.widget_ring_blue
                ORANGE -> R.drawable.widget_ring_orange
                PURPLE -> R.drawable.widget_ring_purple
                RED    -> R.drawable.widget_ring_red
                YELLOW -> R.drawable.widget_ring_yellow
                GREEN  -> R.drawable.widget_ring_green
            }
}



val Int.asWidgetColor
    get() =
        when (this) {
            WidgetColor.BLUE.ordinal   -> WidgetColor.BLUE
            WidgetColor.ORANGE.ordinal -> WidgetColor.ORANGE
            WidgetColor.PURPLE.ordinal -> WidgetColor.PURPLE
            WidgetColor.RED.ordinal    -> WidgetColor.RED
            WidgetColor.YELLOW.ordinal -> WidgetColor.YELLOW
            else                       -> WidgetColor.GREEN
        }