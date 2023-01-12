package ru.veider.profilemanager.ui.preference_activity.assets

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R

// NotificationType ========================================================================
enum class NotificationType(val value: Int) {
    FULL(0), SHORT(1), NONE(2);
}

val NotificationType.desc: String
    @Composable
    get() =
        when (this) {
            NotificationType.FULL  -> stringResource(id = R.string.widget_notification_type_desc_full)
            NotificationType.SHORT -> stringResource(id = R.string.widget_notification_type_desc_short)
            NotificationType.NONE  -> stringResource(id = R.string.widget_notification_type_desc_none)
        }

// BackgroundColor =========================================================================
enum class BackgroundColor(val value: Int) {
    WHITE(0), GREY(1), BLACK(2), NONE(3)
}

val BackgroundColor.desc: String
    @Composable
    get() =
        when (this) {
            BackgroundColor.WHITE -> stringResource(id = R.string.widget_background_color_white)
            BackgroundColor.GREY  -> stringResource(id = R.string.widget_background_color_grey)
            BackgroundColor.BLACK -> stringResource(id = R.string.widget_background_color_black)
            BackgroundColor.NONE  -> stringResource(id = R.string.widget_background_color_none)
        }

// Transparency =============================================================================
data class BackgroundTransparency(
    val value: Int
)
val BackgroundTransparency.desc: String
    @Composable
    get() = when (value) {
        0      -> stringResource(id = R.string.widget_transparency_none)
        in 1..99 -> "${stringResource(id = R.string.widget_transparency_middle)} $value%"
        else   -> stringResource(id = R.string.widget_transparency_full)
    }