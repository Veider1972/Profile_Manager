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

fun getRingtoneTitle(context: Context, uri: Uri): String = RingtoneManager.getRingtone(context, uri).getTitle(context)

fun getScreenTimeout(context: Context): String {
    val timeout = Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT) / 1000
    return getScreenTimeout(context, timeout)
}

fun getScreenTimeout(context: Context, timeout: Int): String {
    if (timeout > 3600)
        context.resources.getString(R.string.text_never)
    return if (timeout > 60) {
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

fun getScreenBrightness(context: Context): Int = Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS) * 100 / 255