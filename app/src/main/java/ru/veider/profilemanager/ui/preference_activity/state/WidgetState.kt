package ru.veider.profilemanager.ui.preference_activity.state

import android.net.Uri
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetBackgroundColor
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetBackgroundTransparency
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetNotificationType

data class WidgetState(
    val notificationType: WidgetNotificationType = WidgetNotificationType.FULL,
    val backgroundColor: WidgetBackgroundColor = WidgetBackgroundColor.GREY,
    val backgroundTransparency: WidgetBackgroundTransparency = WidgetBackgroundTransparency(0)
)