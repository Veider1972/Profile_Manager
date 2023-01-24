package ru.veider.profilemanager.domain

import android.net.Uri
import ru.veider.profilemanager.repo.localrepo.LocalRepo
import ru.veider.profilemanager.ui.preference_activity.assets.enums.*
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState

class UseCasesImpl(
    val localRepo: LocalRepo
) : UseCases {

    override suspend fun getCurrentProfile(): Resource<Long> =
            try {
                when (val profileId = localRepo.getCurrentProfile()){
                    is Resource.Success -> Resource.Success(profileId.data)
                    is Resource.Error -> Resource.Error(profileId.error)
                }
            } catch (e:Exception){
                Resource.Error(e)
            }

    override suspend fun putCurrentProfile(currentProfileId:Long) {
        localRepo.putCurrentProfile(currentProfileId)
    }

    override suspend fun getWidgetSettings(): Resource<WidgetState> =
            try {
                when (val widgetData = localRepo.getWidgetSettings()){
                    is Resource.Success -> Resource.Success(widgetData.data.toWidgetState())
                    is Resource.Error -> Resource.Error(widgetData.error)
                }
            } catch (e:Exception){
                Resource.Error(e)
            }

    override suspend fun putWidgetSettings(currentProfile: WidgetState) {
        localRepo.putWidgetSettings(currentProfile.toWidgetData())
    }

    override suspend fun getProfiles(): Resource<List<ProfileState>> =
            try {
                when (val profiles = localRepo.getProfiles()) {
                    is Resource.Success -> Resource.Success(profiles.data.map {
                        it.toProfileSettingsState()
                    })
                    is Resource.Error -> Resource.Error(profiles.error)
                }
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override suspend fun putProfile(profile: ProfileState) {
        localRepo.putProfile(profile.toProfileData())
    }

    override suspend fun updateWidgetNotificationType(notificationType: WidgetNotificationType){
        localRepo.updateWidgetNotificationType(notificationType.ordinal)
    }

    override suspend fun updateWidgetBackgroundColor(backgroundColor: WidgetBackgroundColor) {
        localRepo.updateWidgetBackgroundColor(backgroundColor.ordinal)
    }

    override suspend fun updateWidgetBackgroundTransparency(backgroundTransparency: WidgetBackgroundTransparency) {
        localRepo.updateWidgetBackgroundTransparency(backgroundTransparency.value)
    }

    override suspend fun updateWidgetNotification(notification: Boolean) {
        localRepo.updateWidgetNotification(notification)
    }

    override suspend fun deleteProfile(id: Long) {
        localRepo.deleteProfile(id)
    }

    override suspend fun update(profile: ProfileState) {
        localRepo.update(profile.toProfileData())
    }

    override suspend fun updateName(name: String, id: Long) {
        localRepo.updateName(name, id)
    }

    override suspend fun updateSymbolColor(symbolColor: WidgetSymbolColor, id: Long) {
        localRepo.updateSymbolColor(symbolColor.ordinal, id)
    }

    override suspend fun updateRingColor(ringColor: WidgetSymbolColor, id: Long) {
        localRepo.updateRingColor(ringColor.ordinal, id)
    }

    override suspend fun updateSymbol(symbol: WidgetSymbol, id: Long) {
        localRepo.updateSymbol(symbol.ordinal, id)
    }

    override suspend fun updateVibration(guideVibration: Boolean, vibrationOn: Boolean, id: Long) {
        localRepo.updateVibration(guideVibration, vibrationOn, id)
    }

    override suspend fun updateVolume(guideVolume: Boolean, commonVolume: Int, useCommonVolume: Boolean, notificationVolume: Int, mediaVolume: Int,
                                      alarmVolume: Int, id: Long) {
        localRepo.updateVolume(guideVolume, commonVolume, useCommonVolume, notificationVolume, mediaVolume, alarmVolume, id)
    }

    override suspend fun updateCallMelody(guideCallMelody: Boolean, callMelody: Uri, id: Long) {
        localRepo.updateCallMelody(guideCallMelody, callMelody.path.toString(), id)
    }

    override suspend fun updateSMSMelody(guideCallMelody: Boolean, smsMelody: Uri, id: Long) {
        localRepo.updateSMSMelody(guideCallMelody, smsMelody.path.toString(), id)
    }

    override suspend fun updateAlarmMelody(guideAlarmMelody: Boolean, alarmMelody: Uri, id: Long) {
        localRepo.updateAlarmMelody(guideAlarmMelody, alarmMelody.path.toString(), id)
    }

    override suspend fun updateWifi(guideWiFi: Boolean, wiFiOn: Boolean, id: Long) {
        localRepo.updateWifi(guideWiFi, wiFiOn, id)
    }

    override suspend fun updateMobileData(guideMobileData: Boolean, mobileDataOn: Boolean, id: Long) {
        localRepo.updateMobileData(guideMobileData, mobileDataOn, id)
    }

    override suspend fun updateBluetooth(guideBluetooth: Boolean, bluetoothOn: Boolean, id: Long) {
        localRepo.updateBluetooth(guideBluetooth, bluetoothOn, id)
    }

    override suspend fun updateAccessPoint(guideAccessPoint: Boolean, accessPointOn: Boolean, id: Long) {
        localRepo.updateAccessPoint(guideAccessPoint, accessPointOn, id)
    }

    override suspend fun updateScreenTimeout(guideScreenTimeout: Boolean, screenTimeout: Int, id: Long) {
        localRepo.updateScreenTimeout(guideScreenTimeout, screenTimeout, id)
    }

    override suspend fun updateScreenBrightness(guideScreenBrightness: Boolean, screenBrightness: Int, id: Long) {
        localRepo.updateScreenBrightness(guideScreenBrightness, screenBrightness, id)
    }

    override suspend fun updateSync(guideSync: Boolean, syncOn: Boolean, id: Long) {
        localRepo.updateSync(guideSync, syncOn, id)
    }

    override suspend fun updateScreenRotation(guideScreenRotation: Boolean, screenRotationOn: Boolean, id: Long) {
        localRepo.updateScreenRotation(guideScreenRotation, screenRotationOn, id)
    }

    override suspend fun updateScreenLock(guideScreenLock: Boolean, screenLockOn: Boolean, id: Long) {
        localRepo.updateScreenLock(guideScreenLock, screenLockOn, id)
    }
}