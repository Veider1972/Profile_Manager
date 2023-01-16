package ru.veider.profilemanager.ui.preference_activity.assets.enum

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.*

enum class WidgetSymbolColor(val value:Int) {
    BLUE(0), GREEN(1), YELLOW(2), ORANGE(3), RED(4), PURPLE(5)
}

val WidgetSymbolColor.desc: String
    @Composable
    get() =
        when (this) {
            WidgetSymbolColor.BLUE -> stringResource(id = R.string.profile_widget_symbol_color_blue)
            WidgetSymbolColor.ORANGE  -> stringResource(id = R.string.profile_widget_symbol_color_orange)
            WidgetSymbolColor.PURPLE -> stringResource(id = R.string.profile_widget_symbol_color_purple)
            WidgetSymbolColor.RED  -> stringResource(id = R.string.profile_widget_symbol_color_red)
            WidgetSymbolColor.YELLOW  -> stringResource(id = R.string.profile_widget_symbol_color_yellow)
            WidgetSymbolColor.GREEN  -> stringResource(id = R.string.profile_widget_symbol_color_green)
        }
val WidgetSymbolColor.color: Color
    @Composable
    get() =
        when (this) {
            WidgetSymbolColor.BLUE -> colorBlue
            WidgetSymbolColor.ORANGE  -> colorOrange
            WidgetSymbolColor.PURPLE -> colorPurple
            WidgetSymbolColor.RED  -> colorRed
            WidgetSymbolColor.YELLOW  -> colorYellow
            WidgetSymbolColor.GREEN  -> colorGreen
        }