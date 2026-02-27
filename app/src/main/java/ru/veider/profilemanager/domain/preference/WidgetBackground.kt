package ru.veider.profilemanager.domain.preference

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
    fun gradient(context: Context) =
            when (this) {
                WHITE -> listOf(Color(context.getColor(R.color.widget_gradient_white_start)), Color(context.getColor(R.color.widget_gradient_white_end)))
                GRAY  -> listOf(Color(context.getColor(R.color.widget_gradient_gray_start)), Color(context.getColor(R.color.widget_gradient_gray_end)))
                BLACK -> listOf(Color(context.getColor(R.color.widget_gradient_black_start)), Color(context.getColor(R.color.widget_gradient_black_end)))
                NONE  -> listOf(Color(context.getColor(R.color.widget_gradient_transparent)), Color(context.getColor(R.color.widget_gradient_transparent)))
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
