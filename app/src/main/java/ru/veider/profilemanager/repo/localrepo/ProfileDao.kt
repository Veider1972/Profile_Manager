package ru.veider.profilemanager.repo.localrepo

import androidx.room.*
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData
import ru.veider.profilemanager.repo.localrepo.entity.ProfileData

@Dao
interface ProfileDao {

    // Текущий профиль
    @Query("SELECT current_profile_id FROM current WHERE id=0")
    suspend fun getCurrentProfile(): Long

    @Query("INSERT INTO current (id, current_profile_id) VALUES (0, :currentProfileId)")
    suspend fun putCurrentProfile(currentProfileId:Long)

    // Текущий виджет
    @Query("SELECT * FROM widget")
    suspend fun getWidgetSettings(): WidgetData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putWidgetSettings(currentProfile: WidgetData)

    @Query("UPDATE widget SET notification_type=:notificationType WHERE id=0")
    suspend fun updateWidgetNotificationType(notificationType:Int)

    @Query("UPDATE widget SET background_color=:backgroundColor WHERE id=0")
    suspend fun updateWidgetBackgroundColor(backgroundColor:Int)

    @Query("UPDATE widget SET background_transparency=:backgroundTransparency WHERE id=0")
    suspend fun updateWidgetBackgroundTransparency(backgroundTransparency:Int)

    @Query("UPDATE widget SET notification=:notification WHERE id=0")
    suspend fun updateWidgetNotification(notification:Boolean)

    // Настройки профилей
    @Query("SELECT * FROM profiles")
    suspend fun getProfiles(): List<ProfileData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putProfile(profile: ProfileData)

    @Query("DELETE FROM profiles WHERE id=:id")
    suspend fun deleteProfile(id: Long)

    @Update
    suspend fun update(profile: ProfileData)

    @Query("UPDATE profiles SET name=:name WHERE id=:id")
    suspend fun updateName(name: String, id: Long)

    @Query("UPDATE profiles SET symbol_color=:symbolColor WHERE id=:id")
    suspend fun updateSymbolColor(symbolColor: Int, id: Long)

    @Query("UPDATE profiles SET ring_color=:ringColor WHERE id=:id")
    suspend fun updateRingColor(ringColor: Int, id: Long)

    @Query("UPDATE profiles SET symbol=:symbol WHERE id=:id")
    suspend fun updateSymbol(symbol: Int, id: Long)

    @Query("UPDATE profiles SET guide_vibration=:guideVibration, vibration_on=:vibrationOn WHERE id=:id")
    suspend fun updateVibration(guideVibration: Boolean, vibrationOn: Boolean, id: Long)

    @Query("UPDATE profiles SET guide_volume=:guideVolume, common_volume=:commonVolume, use_common_volume=:useCommonVolume, notification_volume=:notificationVolume, media_volume=:mediaVolume, alarm_volume=:alarmVolume WHERE id=:id")
    suspend fun updateVolume(guideVolume: Boolean, commonVolume: Int, useCommonVolume: Boolean, notificationVolume: Int, mediaVolume: Int, alarmVolume: Int, id: Long)

    @Query("UPDATE profiles SET guide_call_melody=:guideCallMelody, call_melody=:callMelody WHERE id=:id")
    suspend fun updateCallMelody(guideCallMelody: Boolean, callMelody: String, id: Long)

    @Query("UPDATE profiles SET guide_sms_melody=:guideSMSMelody, sms_melody=:smsMelody WHERE id=:id")
    suspend fun updateSMSMelody(guideSMSMelody: Boolean, smsMelody: String, id: Long)

    @Query("UPDATE profiles SET guide_alarm_melody=:guideAlarmMelody, alarm_melody=:alarmMelody WHERE id=:id")
    suspend fun updateAlarmMelody(guideAlarmMelody: Boolean, alarmMelody: String, id: Long)

    @Query("UPDATE profiles SET guide_wifi=:guideWiFi, wifi_on=:wiFiOn WHERE id=:id")
    suspend fun updateWifi(guideWiFi: Boolean, wiFiOn: Boolean, id: Long)

    @Query("UPDATE profiles SET guide_mobile_data=:guideMobileData, mobile_data_on=:mobileDataOn WHERE id=:id")
    suspend fun updateMobileData(guideMobileData: Boolean, mobileDataOn: Boolean, id: Long)

    @Query("UPDATE profiles SET guide_bluetooth=:guideBluetooth, bluetooth_on=:bluetoothOn WHERE id=:id")
    suspend fun updateBluetooth(guideBluetooth: Boolean, bluetoothOn: Boolean, id: Long)

    @Query("UPDATE profiles SET guide_access_point=:guideAccessPoint, access_point_on=:accessPointOn WHERE id=:id")
    suspend fun updateAccessPoint(guideAccessPoint: Boolean, accessPointOn: Boolean, id: Long)

    @Query("UPDATE profiles SET guide_screen_timeout=:guideScreenTimeout, screen_timeout=:screenTimeout WHERE id=:id")
    suspend fun updateScreenTimeout(guideScreenTimeout: Boolean, screenTimeout: Int, id: Long)

    @Query("UPDATE profiles SET guide_screen_brightness=:guideScreenBrightness, screen_brightness=:screenBrightness WHERE id=:id")
    suspend fun updateScreenBrightness(guideScreenBrightness: Boolean, screenBrightness: Int, id: Long)

    @Query("UPDATE profiles SET guide_sync=:guideSync, sync_on=:syncOn WHERE id=:id")
    suspend fun updateSync(guideSync: Boolean, syncOn: Boolean, id: Long)

    @Query("UPDATE profiles SET guide_screen_rotation=:guideScreenRotation, screen_rotation_on=:screenRotationOn WHERE id=:id")
    suspend fun updateScreenRotation(guideScreenRotation: Boolean, screenRotationOn: Boolean, id: Long)

    @Query("UPDATE profiles SET guide_screen_lock=:guideScreenLock, screen_lock_on=:screenLockOn WHERE id=:id")
    suspend fun updateScreenLock(guideScreenLock: Boolean, screenLockOn: Boolean, id: Long)

}