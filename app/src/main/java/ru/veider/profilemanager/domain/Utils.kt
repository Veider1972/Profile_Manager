package ru.veider.profilemanager.domain

import ru.veider.profilemanager.domain.preference.WidgetBackgroundTransparency
import ru.veider.profilemanager.domain.preference.asWidgetBackground
import ru.veider.profilemanager.domain.preference.asWidgetNotificationType
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState

//fun ProfileData.toProfileSettingsState() =
//    ProfileState(
//        id = id,
//        name = name,
//        symbolColor = symbolColor,
//        ringColor = ringColor,
//        symbol = symbol,
//        volumeMode = volumeMode,
//        ringVolume = rin,
//        notificationVolume = notificationVolume,
//        mediaVolume = mediaVolume,
//        alarmVolume = alarmVolume,
//        guideScreenTimeout = guideScreenTimeout,
//        screenTimeout = screenTimeout,
//        guideScreenBrightness = guideScreenBrightness,
//        screenBrightness = screenBrightness
//    )
//
//fun ProfileState.toProfileData() =
//    ProfileData(
//        id = id,
//        name = name,
//        symbolColor = symbolColor,
//        ringColor = ringColor,
//        symbol = symbol,
//        volumeMode = volumeMode,
//        commonVolume = ringVolume,
//        useCommonVolume = useRingVolume,
//        notificationVolume = notificationVolume,
//        mediaVolume = mediaVolume,
//        alarmVolume = alarmVolume,
//        guideScreenTimeout = guideScreenTimeout,
//        screenTimeout = screenTimeout,
//        guideScreenBrightness = guideScreenBrightness,
//        screenBrightness = screenBrightness,
//    )
//
//fun WidgetData.toWidgetState() =
//    WidgetState(
//        notificationType = this.notificationType.asWidgetNotificationType,
//        backgroundColor = this.backgroundColor.asWidgetBackground,
//        backgroundTransparency = WidgetBackgroundTransparency(this.backgroundTransparency)
//    )
//
//fun WidgetState.toWidgetData() =
//    WidgetData(
//        id = 0,
//        notificationType = this.notificationType.ordinal,
//        backgroundColor = this.backgroundColor.ordinal,
//        backgroundTransparency = this.backgroundTransparency.value,
//        notification = this.notificationOn
//    )