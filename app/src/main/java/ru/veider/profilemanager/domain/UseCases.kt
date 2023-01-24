package ru.veider.profilemanager.domain

import android.net.Uri
import ru.veider.profilemanager.ui.preference_activity.assets.enums.*
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState

interface UseCases {
    suspend fun getCurrentProfile(): Resource<Long>
    suspend fun putCurrentProfile(currentProfileId:Long)
    suspend fun getWidgetSettings(): Resource<WidgetState>
    suspend fun putWidgetSettings(currentProfile: WidgetState)
    suspend fun getProfiles(): Resource<List<ProfileState>>
    suspend fun putProfile(profile: ProfileState)
    suspend fun updateWidgetNotificationType(notificationType:WidgetNotificationType)
    suspend fun updateWidgetBackgroundColor(backgroundColor:WidgetBackgroundColor)
    suspend fun updateWidgetBackgroundTransparency(backgroundTransparency:WidgetBackgroundTransparency)
    suspend fun updateWidgetNotification(notification:Boolean)
    suspend fun deleteProfile(id: Long)
    suspend fun update(profile: ProfileState)
    suspend fun updateName(name: String, id: Long)
    suspend fun updateSymbolColor(symbolColor: WidgetSymbolColor, id: Long)
    suspend fun updateRingColor(ringColor: WidgetSymbolColor, id: Long)
    suspend fun updateSymbol(symbol: WidgetSymbol, id: Long)
    suspend fun updateVibration(guideVibration: Boolean, vibrationOn: Boolean, id: Long)
    suspend fun updateVolume(guideVolume: Boolean, commonVolume: Int, useCommonVolume: Boolean, notificationVolume: Int, mediaVolume: Int, alarmVolume: Int, id: Long)
    suspend fun updateCallMelody(guideCallMelody: Boolean, callMelody: Uri, id: Long)
    suspend fun updateSMSMelody(guideCallMelody: Boolean, smsMelody: Uri, id: Long)
    suspend fun updateAlarmMelody(guideAlarmMelody: Boolean, alarmMelody: Uri, id: Long)
    suspend fun updateWifi(guideWiFi: Boolean, wiFiOn: Boolean, id: Long)
    suspend fun updateMobileData(guideMobileData: Boolean, mobileDataOn: Boolean, id: Long)
    suspend fun updateBluetooth(guideBluetooth: Boolean, bluetoothOn: Boolean, id: Long)
    suspend fun updateAccessPoint(guideAccessPoint: Boolean, accessPointOn: Boolean, id: Long)
    suspend fun updateScreenTimeout(guideScreenTimeout: Boolean, screenTimeout: Int, id: Long)
    suspend fun updateScreenBrightness(guideScreenBrightness: Boolean, screenBrightness: Int, id: Long)
    suspend fun updateSync(guideSync: Boolean, syncOn: Boolean, id: Long)
    suspend fun updateScreenRotation(guideScreenRotation: Boolean, screenRotationOn: Boolean, id: Long)
    suspend fun updateScreenLock(guideScreenLock: Boolean, screenLockOn: Boolean, id: Long)
}