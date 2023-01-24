package ru.veider.profilemanager.ui.preference_activity.state

import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetBackgroundColor
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetBackgroundTransparency
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetNotificationType

data class WidgetState(
    var notificationType: WidgetNotificationType = WidgetNotificationType.FULL,
    var backgroundColor: WidgetBackgroundColor = WidgetBackgroundColor.GREY,
    var backgroundTransparency: WidgetBackgroundTransparency = WidgetBackgroundTransparency(0),
    var notificationOn: Boolean = false
)