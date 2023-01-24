package ru.veider.profilemanager.ui.preference_activity.assets.enums

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R

enum class WidgetNotificationType {
    FULL, SHORT, NONE;
    val desc
        @Composable
        get() =
            when (this) {
                FULL  -> stringResource(id = R.string.widget_notification_type_desc_full)
                SHORT -> stringResource(id = R.string.widget_notification_type_desc_short)
                NONE  -> stringResource(id = R.string.widget_notification_type_desc_none)
            }
}

val Int.asWidgetNotificationType
    get()=
        when (this){
            WidgetNotificationType.FULL.ordinal  -> WidgetNotificationType.FULL
            WidgetNotificationType.SHORT.ordinal -> WidgetNotificationType.SHORT
            else                                 -> WidgetNotificationType.NONE
        }