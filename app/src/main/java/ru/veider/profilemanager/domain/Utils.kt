package ru.veider.profilemanager.domain

import android.net.Uri
import ru.veider.profilemanager.repo.localrepo.entity.ProfileData
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData
import ru.veider.profilemanager.ui.preference_activity.assets.enums.*
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState

fun ProfileData.toProfileSettingsState() =
        ProfileState(
            id = id,
            name = name,
            symbolColor = symbolColor.asWidgetSymbolColor,
            ringColor = ringColor.asWidgetSymbolColor,
            symbol = symbol.asWidgetSymbol,
            guideVibration = guideVibration,
            vibrationOn = vibrationOn,
            guideVolume = guideVolume,
            commonVolume = commonVolume,
            useCommonVolume = useCommonVolume,
            notificationVolume = notificationVolume,
            mediaVolume = mediaVolume,
            alarmVolume = alarmVolume,
            guideCallMelody = guideCallMelody,
            callMelody = Uri.parse(callMelody),
            guideSMSMelody = guideSMSMelody,
            smsMelody = Uri.parse(smsMelody),
            guideAlarmMelody = guideAlarmMelody,
            alarmMelody = Uri.parse(alarmMelody),
            guideWiFi = guideWiFi,
            wiFiOn = wiFiOn,
            guideMobileData = guideMobileData,
            mobileDataOn = mobileDataOn,
            guideBluetooth = guideBluetooth,
            bluetoothOn = bluetoothOn,
            guideAccessPoint = guideAccessPoint,
            accessPointOn = accessPointOn,
            guideScreenTimeout = guideScreenTimeout,
            screenTimeout = screenTimeout,
            guideScreenBrightness = guideScreenBrightness,
            screenBrightness = screenBrightness,
            guideSync = guideSync,
            syncOn = syncOn,
            guideScreenRotation = guideScreenRotation,
            screenRotationOn = screenRotationOn,
            guideScreenLock = guideScreenLock,
            screenLockOn = screenLockOn
        )

fun ProfileState.toProfileData() =
        ProfileData(
            id = id,
            name = name,
            symbolColor = symbolColor.ordinal,
            ringColor = ringColor.ordinal,
            symbol = symbol.ordinal,
            guideVibration = guideVibration,
            vibrationOn = vibrationOn,
            guideVolume = guideVolume,
            commonVolume = commonVolume,
            useCommonVolume = useCommonVolume,
            notificationVolume = notificationVolume,
            mediaVolume = mediaVolume,
            alarmVolume = alarmVolume,
            guideCallMelody = guideCallMelody,
            callMelody = callMelody.path.toString(),
            guideSMSMelody = guideSMSMelody,
            smsMelody = smsMelody.path.toString(),
            guideAlarmMelody = guideAlarmMelody,
            alarmMelody = alarmMelody.path.toString(),
            guideWiFi = guideWiFi,
            wiFiOn = wiFiOn,
            guideMobileData = guideMobileData,
            mobileDataOn = mobileDataOn,
            guideBluetooth = guideBluetooth,
            bluetoothOn = bluetoothOn,
            guideAccessPoint = guideAccessPoint,
            accessPointOn = accessPointOn,
            guideScreenTimeout = guideScreenTimeout,
            screenTimeout = screenTimeout,
            guideScreenBrightness = guideScreenBrightness,
            screenBrightness = screenBrightness,
            guideSync = guideSync,
            syncOn = syncOn,
            guideScreenRotation = guideScreenRotation,
            screenRotationOn = screenRotationOn,
            guideScreenLock = guideScreenLock,
            screenLockOn = screenLockOn
        )

fun WidgetData.toWidgetState() =
        WidgetState(
            notificationType = this.notificationType.asWidgetNotificationType,
            backgroundColor = this.backgroundColor.asWidgetBackgroundColor,
            backgroundTransparency = WidgetBackgroundTransparency(this.backgroundTransparency)
        )

fun WidgetState.toWidgetData() =
        WidgetData(
            id=0,
            notificationType = this.notificationType.ordinal,
            backgroundColor = this.backgroundColor.ordinal,
            backgroundTransparency = this.backgroundTransparency.value,
            notification = this.notificationOn
        )