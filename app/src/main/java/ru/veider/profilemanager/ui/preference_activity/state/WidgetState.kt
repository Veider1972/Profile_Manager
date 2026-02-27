package ru.veider.profilemanager.ui.preference_activity.state

import ru.veider.profilemanager.domain.preference.WidgetBackground
import ru.veider.profilemanager.domain.preference.WidgetBackgroundTransparency
import ru.veider.profilemanager.domain.preference.WidgetNotificationType

data class WidgetState(
    var notificationType: WidgetNotificationType = WidgetNotificationType.FULL,
    var backgroundColor: WidgetBackground = WidgetBackground.GRAY,
    var backgroundTransparency: WidgetBackgroundTransparency = WidgetBackgroundTransparency(0),
    var notificationOn: Boolean = false
)