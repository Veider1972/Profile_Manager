package ru.veider.profilemanager.ui.preference_activity.assets.enums

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.*

enum class WidgetSymbolColor {
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
}



val Int.asWidgetSymbolColor
    get() =
        when (this) {
            WidgetSymbolColor.BLUE.ordinal   -> WidgetSymbolColor.BLUE
            WidgetSymbolColor.ORANGE.ordinal -> WidgetSymbolColor.ORANGE
            WidgetSymbolColor.PURPLE.ordinal -> WidgetSymbolColor.PURPLE
            WidgetSymbolColor.RED.ordinal    -> WidgetSymbolColor.RED
            WidgetSymbolColor.YELLOW.ordinal -> WidgetSymbolColor.YELLOW
            else                             -> WidgetSymbolColor.GREEN
        }