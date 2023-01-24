package ru.veider.profilemanager.repo.localrepo

import ru.veider.profilemanager.repo.localrepo.entity.ProfileData
import ru.veider.profilemanager.domain.Resource
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData

interface LocalRepo {
    suspend fun getCurrentProfile(): Resource<Long>
    suspend fun putCurrentProfile(currentProfileId: Long)
    suspend fun getWidgetSettings(): Resource<WidgetData>
    suspend fun putWidgetSettings(currentProfile: WidgetData)
    suspend fun updateWidgetNotificationType(notificationType: Int)
    suspend fun updateWidgetBackgroundColor(backgroundColor: Int)
    suspend fun updateWidgetBackgroundTransparency(backgroundTransparency: Int)
    suspend fun updateWidgetNotification(notificationOn: Boolean)
    suspend fun getProfiles(): Resource<List<ProfileData>>
    suspend fun putProfile(profile: ProfileData)
    suspend fun deleteProfile(id: Long)
    suspend fun update(profile: ProfileData)
    suspend fun updateName(name: String, id: Long)
    suspend fun updateSymbolColor(symbolColor: Int, id: Long)
    suspend fun updateRingColor(ringColor: Int, id: Long)
    suspend fun updateSymbol(symbol: Int, id: Long)
    suspend fun updateVibration(guideVibration: Boolean, vibrationOn: Boolean, id: Long)
    suspend fun updateVolume(guideVolume: Boolean, commonVolume: Int, useCommonVolume: Boolean, notificationVolume: Int,
                             mediaVolume: Int, alarmVolume: Int, id: Long)
    suspend fun updateCallMelody(guideCallMelody: Boolean, callMelody: String, id: Long)
    suspend fun updateSMSMelody(guideCallMelody: Boolean, smsMelody: String, id: Long)
    suspend fun updateAlarmMelody(guideAlarmMelody: Boolean, alarmMelody: String, id: Long)
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