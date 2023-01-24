package ru.veider.profilemanager.repo.localrepo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileData(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol_color") val symbolColor: Int,
    @ColumnInfo(name = "ring_color") val ringColor: Int,
    @ColumnInfo(name = "symbol") val symbol: Int,
    @ColumnInfo(name = "guide_vibration") val guideVibration: Boolean,
    @ColumnInfo(name = "vibration_on") val vibrationOn: Boolean,
    @ColumnInfo(name = "guide_volume") val guideVolume: Boolean,
    @ColumnInfo(name = "common_volume") val commonVolume: Int,
    @ColumnInfo(name = "use_common_volume") val useCommonVolume: Boolean,
    @ColumnInfo(name = "notification_volume") val notificationVolume: Int,
    @ColumnInfo(name = "media_volume") val mediaVolume: Int,
    @ColumnInfo(name = "alarm_volume") val alarmVolume: Int,
    @ColumnInfo(name = "guide_call_melody") val guideCallMelody: Boolean,
    @ColumnInfo(name = "call_melody") val callMelody: String,
    @ColumnInfo(name = "guide_sms_melody") val guideSMSMelody: Boolean,
    @ColumnInfo(name = "sms_melody") val smsMelody: String,
    @ColumnInfo(name = "guide_alarm_melody") val guideAlarmMelody: Boolean,
    @ColumnInfo(name = "alarm_melody") val alarmMelody: String,
    @ColumnInfo(name = "guide_wifi") val guideWiFi: Boolean,
    @ColumnInfo(name = "wifi_on") val wiFiOn: Boolean,
    @ColumnInfo(name = "guide_mobile_data") val guideMobileData: Boolean,
    @ColumnInfo(name = "mobile_data_on") val mobileDataOn: Boolean,
    @ColumnInfo(name = "guide_bluetooth") val guideBluetooth: Boolean,
    @ColumnInfo(name = "bluetooth_on") val bluetoothOn: Boolean,
    @ColumnInfo(name = "guide_access_point") val guideAccessPoint: Boolean,
    @ColumnInfo(name="access_point_on") val accessPointOn: Boolean,
    @ColumnInfo(name="guide_screen_timeout") val guideScreenTimeout: Boolean,
    @ColumnInfo(name="screen_timeout") val screenTimeout: Int,
    @ColumnInfo(name="guide_screen_brightness") val guideScreenBrightness: Boolean,
    @ColumnInfo(name="screen_brightness") val screenBrightness: Int,
    @ColumnInfo(name="guide_sync") val guideSync: Boolean,
    @ColumnInfo(name="sync_on") val syncOn: Boolean,
    @ColumnInfo(name="guide_screen_rotation") val guideScreenRotation: Boolean,
    @ColumnInfo(name="screen_rotation_on") val screenRotationOn: Boolean,
    @ColumnInfo(name="guide_screen_lock") val guideScreenLock: Boolean,
    @ColumnInfo(name="screen_lock_on") val screenLockOn: Boolean
)