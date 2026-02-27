package ru.veider.profilemanager.ui.preference_activity.assets

import android.content.Context
import android.media.AudioManager
import android.media.RingtoneManager
import android.net.*
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.state.PhoneCapabilities

const val TAG = "VVVVV"

val Int.toDp: Dp
    @Composable
    get() = (this / LocalContext.current.resources.displayMetrics.density).dp

fun Int.toDp(context: Context): Dp = (this / context.resources.displayMetrics.density).dp

val Float.toDp: Dp
    @Composable
    get() = (this * LocalContext.current.resources.displayMetrics.scaledDensity / LocalContext.current.resources.displayMetrics.density).dp

fun Float.toDp(context: Context): Dp =
    (this * context.resources.displayMetrics.scaledDensity / context.resources.displayMetrics.density).dp

val Boolean.toInt: Int
    get() = if (this) 1 else 0

fun getRingtoneTitle(context: Context, uri: Uri): String = RingtoneManager.getRingtone(context, uri).getTitle(context)

fun getScreenTimeout(context: Context): String {
    val timeout = Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT) / 1000
    return getScreenTimeout(context, timeout)
}

fun getScreenTimeout(context: Context, timeout: Int): String {
    return if (timeout > 3600)
        context.resources.getString(R.string.text_never)
    else if (timeout == 0) {
        context.resources.getString(R.string.text_continuously)
    } else if (timeout > 60) {
        val minutes = timeout / 60
        val seconds = timeout - 60 * minutes

        "$minutes ${context.resources.getString(R.string.text_minutes)}${
            if (seconds != 0) "$seconds ${
                context.resources.getString(R.string.text_seconds)
            }" else ""
        } "
    } else {
        "$timeout ${context.resources.getString(R.string.text_seconds)}"
    }
}

fun getPairTimeout(timeout: Int): Pair<Int, Int> {
    if (timeout > 3600)
        Pair(Int.MAX_VALUE, Int.MAX_VALUE)
    return if (timeout > 60) {
        val minutes = timeout / 60
        val seconds = timeout - 60 * minutes

        Pair(minutes, seconds)
    } else {
        Pair(0, timeout)
    }
}

fun getScreenBrightness(context: Context): Int =
    Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS) * 100 / 255

fun getScreenBrightnessAuto(context: Context): Boolean =
    Settings.System.getInt(
        context.contentResolver,
        Settings.System.SCREEN_BRIGHTNESS_MODE
    ) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC

fun setVolume(
    context: Context, ringVolume: Int, notificationVolume: Int, mediaVolume: Int,
    alarmVolume: Int
) {
    val capability = PhoneCapabilities(context)
    val audioManager = context.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    audioManager.setStreamVolume(
        AudioManager.STREAM_RING, (capability.normalRingVolume * ringVolume).toInt(), 0
    )
    audioManager.setStreamVolume(
        AudioManager.STREAM_NOTIFICATION, (capability.normalNotificationVolume * notificationVolume).toInt(), 0
    )
    audioManager.setStreamVolume(
        AudioManager.STREAM_MUSIC, (capability.normalMusicVolume * mediaVolume).toInt(), 0
    )
    audioManager.setStreamVolume(
        AudioManager.STREAM_ALARM, (capability.normalAlarmVolume * alarmVolume).toInt(), 0
    )
}

val ConnectivityManager.isMobileDataOn: Boolean
    get() = this.getNetworkCapabilities(this.activeNetwork)
        ?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        ?: false