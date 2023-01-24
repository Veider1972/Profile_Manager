package ru.veider.profilemanager.repo.localrepo

import ru.veider.profilemanager.repo.localrepo.entity.ProfileData
import ru.veider.profilemanager.domain.Resource
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData

class LocalRepoImpl(
    private val db: ProfileDatabase
) : LocalRepo {

    override suspend fun getCurrentProfile(): Resource<Long> =
            try {
                Resource.Success(db.profileDao().getCurrentProfile())
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override suspend fun putCurrentProfile(currentProfileId: Long) {
        db.profileDao().putCurrentProfile(currentProfileId)
    }

    override suspend fun getWidgetSettings(): Resource<WidgetData> =
            try {
                Resource.Success(db.profileDao().getWidgetSettings())
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override suspend fun putWidgetSettings(currentProfile: WidgetData){
        db.profileDao().putWidgetSettings(currentProfile)
    }

    override suspend fun updateWidgetNotificationType(notificationType:Int){
        db.profileDao().updateWidgetNotificationType(notificationType)
    }

    override suspend fun updateWidgetBackgroundColor(backgroundColor: Int) {
        db.profileDao().updateWidgetBackgroundColor(backgroundColor)
    }

    override suspend fun updateWidgetBackgroundTransparency(backgroundTransparency: Int) {
        db.profileDao().updateWidgetBackgroundTransparency(backgroundTransparency)
    }

    override suspend fun updateWidgetNotification(notificationOn: Boolean) {
        db.profileDao().updateWidgetNotification(notificationOn)
    }

    override suspend fun getProfiles(): Resource<List<ProfileData>> =
            try {
                Resource.Success(db.profileDao().getProfiles())
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override suspend fun putProfile(profile: ProfileData) {
        db.profileDao().putProfile(profile)
    }

    override suspend fun deleteProfile(profileId: Long) {
        db.profileDao().deleteProfile(profileId)
    }

    override suspend fun update(profile: ProfileData) {
        db.profileDao().update(profile)
    }

    override suspend fun updateName(name: String, id: Long) {
        db.profileDao().updateName(name, id)
    }

    override suspend fun updateSymbolColor(symbolColor: Int, id: Long) {
        db.profileDao().updateSymbolColor(symbolColor, id)
    }

    override suspend fun updateRingColor(ringColor: Int, id: Long) {
        db.profileDao().updateRingColor(ringColor, id)
    }

    override suspend fun updateSymbol(symbol: Int, id: Long) {
        db.profileDao().updateSymbol(symbol, id)
    }

    override suspend fun updateVibration(guideVibration: Boolean, vibrationOn: Boolean, id: Long) {
        db.profileDao().updateVibration(guideVibration, vibrationOn, id)
    }

    override suspend fun updateVolume(guideVolume: Boolean, commonVolume: Int, useCommonVolume: Boolean, notificationVolume: Int, mediaVolume: Int,
                                      alarmVolume: Int, id: Long) {
        db.profileDao().updateVolume(guideVolume, commonVolume, useCommonVolume, notificationVolume, mediaVolume, alarmVolume, id)
    }

    override suspend fun updateCallMelody(guideCallMelody: Boolean, callMelody: String, id: Long) {
        db.profileDao().updateCallMelody(guideCallMelody, callMelody, id)
    }

    override suspend fun updateSMSMelody(guideCallMelody: Boolean, smsMelody: String, id: Long) {
        db.profileDao().updateSMSMelody(guideCallMelody, smsMelody, id)
    }

    override suspend fun updateAlarmMelody(guideAlarmMelody: Boolean, alarmMelody: String, id: Long) {
        db.profileDao().updateAlarmMelody(guideAlarmMelody, alarmMelody, id)
    }

    override suspend fun updateWifi(guideWiFi: Boolean, wiFiOn: Boolean, id: Long) {
        db.profileDao().updateWifi(guideWiFi, wiFiOn, id)
    }

    override suspend fun updateMobileData(guideMobileData: Boolean, mobileDataOn: Boolean, id: Long) {
        db.profileDao().updateMobileData(guideMobileData, mobileDataOn, id)
    }

    override suspend fun updateBluetooth(guideBluetooth: Boolean, bluetoothOn: Boolean, id: Long) {
        db.profileDao().updateBluetooth(guideBluetooth, bluetoothOn, id)
    }

    override suspend fun updateAccessPoint(guideAccessPoint: Boolean, accessPointOn: Boolean, id: Long) {
        db.profileDao().updateAccessPoint(guideAccessPoint, accessPointOn, id)
    }

    override suspend fun updateScreenTimeout(guideScreenTimeout: Boolean, screenTimeout: Int, id: Long) {
        db.profileDao().updateScreenTimeout(guideScreenTimeout, screenTimeout, id)
    }

    override suspend fun updateScreenBrightness(guideScreenBrightness: Boolean, screenBrightness: Int, id: Long) {
        db.profileDao().updateScreenBrightness(guideScreenBrightness, screenBrightness, id)
    }

    override suspend fun updateSync(guideSync: Boolean, syncOn: Boolean, id: Long) {
        db.profileDao().updateSync(guideSync, syncOn, id)
    }

    override suspend fun updateScreenRotation(guideScreenRotation: Boolean, screenRotationOn: Boolean, id: Long) {
        db.profileDao().updateScreenRotation(guideScreenRotation, screenRotationOn, id)
    }

    override suspend fun updateScreenLock(guideScreenLock: Boolean, screenLockOn: Boolean, id: Long) {
        db.profileDao().updateScreenLock(guideScreenLock, screenLockOn, id)
    }
}