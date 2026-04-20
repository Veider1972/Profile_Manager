package ru.veider.profilemanager.data

import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager

class PhoneCapabilities(private val app: Context) {
    val canVibrate
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibrator = app.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibrator.defaultVibrator.hasVibrator()
        } else {
            val vibrator = app.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.hasVibrator()
        }
    val maxRingVolume by lazy {
        val audioManager = app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
    }
    val maxNotificationVolume by lazy {
        val audioManager = app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION)
    }
    val maxMusicVolume by lazy {
        val audioManager = app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    }
    val maxAlarmVolume by lazy {
        val audioManager = app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)
    }
}