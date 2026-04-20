package ru.veider.profilemanager.utils

import android.content.Context
import android.media.AudioManager
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.unit.*
import ru.veider.profilemanager.data.PhoneCapabilities
import ru.veider.profilemanager.domain.*

val Int.toDp: Dp
    @Composable
    get() = (this / LocalResources.current.displayMetrics.density).dp

fun Context.getScreenBrightnessAuto(): Boolean =
    Settings.System.getInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC

fun Context.setScreenBrightness(value: Int?) {
    if (value == null) {
        Log.d("SetProfile", "Устанавливаю яркость: автоматически")
        Settings.System.putInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
    } else {
        Settings.System.putInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
        Settings.System.putInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS, (value * 2.55).toInt()).also {
            Log.d("Brightness", "Устанавливаю яркость: $value")
        }
    }
}

fun Context.setVolume(
    ringVolume: Int,
    notificationVolume: Int,
    mediaVolume: Int,
    alarmVolume: Int
) {
    val capability = PhoneCapabilities(this)
    val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    Log.d("SetProfile", "Устанавливаю громкость звонка: ${capability.maxRingVolume * ringVolume} (maxRingVolume=${capability.maxRingVolume} ringVolume=$ringVolume")
    audioManager.setStreamVolume(
        AudioManager.STREAM_RING, (ringVolume), 0
    )
    Log.d("SetProfile", "Устанавливаю громкость уведомлений: ${notificationVolume} (maxNotificationVolume=${capability.maxNotificationVolume} notificationVolume=$notificationVolume")
    audioManager.setStreamVolume(
        AudioManager.STREAM_NOTIFICATION, (notificationVolume), 0
    )
    Log.d("SetProfile", "Устанавливаю громкость мультимедиа: ${mediaVolume} (maxMusicVolume=${capability.maxMusicVolume} mediaVolume=$mediaVolume")
    audioManager.setStreamVolume(
        AudioManager.STREAM_MUSIC, (mediaVolume), 0
    )
    Log.d("SetProfile", "Устанавливаю громкость будильника: ${capability.maxAlarmVolume * alarmVolume} (maxAlarmVolume=${capability.maxAlarmVolume} alarmVolume=$alarmVolume")
    audioManager.setStreamVolume(
        AudioManager.STREAM_ALARM, (alarmVolume), 0
    )
}

fun Context.setAloudMode() {
    val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    Log.d("SetProfile", "Устанавливаю AudioManager.RINGER_MODE_NORMAL")
    audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
}

fun Context.setVibroMode() {
    val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    Log.d("SetProfile", "Устанавливаю AudioManager.RINGER_MODE_VIBRATE")
    audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
}

fun Context.setSilentMode() {
    val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    Log.d("SetProfile", "Устанавливаю AudioManager.RINGER_MODE_SILENT")
    audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
}

fun Profile.setProfile(context: Context) {
    when (this.type) {
        ProfileType.Day,
        ProfileType.Night,
        ProfileType.Quiet,
        ProfileType.Muted,
        ProfileType.Aloud -> {
            context.setAloudMode()
            context.setVolume(
                ringVolume = ringVolume,
                notificationVolume = if (useCommonVolume) ringVolume else notificationVolume,
                mediaVolume = if (useCommonVolume) ringVolume else mediaVolume,
                alarmVolume = if (useCommonVolume) ringVolume else alarmVolume,
            )
        }
        ProfileType.Vibro -> {
            context.setVibroMode()
            context.setVolume(
                ringVolume = 0,
                notificationVolume = 0,
                mediaVolume = 0,
                alarmVolume = 0,
            )
        }

        ProfileType.Silent -> {
            context.setSilentMode()
            context.setVolume(
                ringVolume = 0,
                notificationVolume = 0,
                mediaVolume = 0,
                alarmVolume = 0,
            )
        }
    }
    if (guideBrightness)
        context.setScreenBrightness(brightness)
}