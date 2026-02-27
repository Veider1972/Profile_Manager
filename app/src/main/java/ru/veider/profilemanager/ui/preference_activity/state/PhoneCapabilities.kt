package ru.veider.profilemanager.ui.preference_activity.state

import android.bluetooth.BluetoothManager
import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager

class PhoneCapabilities(private val app: Context) {
    val hasBluetooth
        get() = run {
            val bluetoothManager = app.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            bluetoothManager.adapter != null
        }
    val canVibrate
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibrator = app.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibrator.defaultVibrator.hasVibrator()
        } else {
            val vibrator = app.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.hasVibrator()
        }
    val normalRingVolume by lazy {
        val audioManager = app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.getStreamMaxVolume(AudioManager.STREAM_RING) / 100f
    }
    val normalNotificationVolume by lazy {
        val audioManager = app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION) / 100f
    }
    val normalMusicVolume by lazy {
        val audioManager = app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) / 100f
    }
    val normalAlarmVolume by lazy {
        val audioManager = app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM) / 100f
    }
}
