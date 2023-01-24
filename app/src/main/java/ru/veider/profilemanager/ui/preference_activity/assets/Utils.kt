package ru.veider.profilemanager.ui.preference_activity.assets

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R

val Int.toDp: Dp
    @Composable
    get() = (this / LocalContext.current.resources.displayMetrics.density).dp

fun Int.toDp(context: Context): Dp = (this / context.resources.displayMetrics.density).dp

val Float.toDp: Dp
    @Composable
    get() = (this * LocalContext.current.resources.displayMetrics.scaledDensity / LocalContext.current.resources.displayMetrics.density).dp

fun Float.toDp(context: Context): Dp =
        (this * context.resources.displayMetrics.scaledDensity / context.resources.displayMetrics.density).dp

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

fun getScreenBrightness(context: Context): Int = Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS) * 100 / 255
fun getScreenBrightnessAuto(context: Context): Boolean =
        Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
