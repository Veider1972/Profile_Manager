package ru.veider.profilemanager.ui.preference_activity.assets.enum

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R

enum class WidgetNotificationType(val value: Int) {
    FULL(0), SHORT(1), NONE(2);
}

val WidgetNotificationType.desc: String
    @Composable
    get() =
        when (this) {
            WidgetNotificationType.FULL  -> stringResource(id = R.string.widget_notification_type_desc_full)
            WidgetNotificationType.SHORT -> stringResource(id = R.string.widget_notification_type_desc_short)
            WidgetNotificationType.NONE  -> stringResource(id = R.string.widget_notification_type_desc_none)
        }