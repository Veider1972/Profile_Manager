package ru.veider.profilemanager.repo

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.*
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.domain.preference.*
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState
import ru.veider.profilemanager.utils.stateFlow

class PreferenceImpl(
    context: Context,
    gson: Gson
) : Preference, BasePreference(context, gson) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var mutex = Mutex()
    private fun CoroutineScope.withMutex(content: () -> Unit) =
        scope.launch {
            mutex.withLock {
                content()
            }
        }

    override val widget = stateFlow(
        initialValue = getObject<WidgetState>("widget") ?: WidgetState(),
        onValueChange = {
            scope.withMutex { putObject("widget", it) }
        }
    )

    override val profiles = stateFlow(
        initialValue = getList<Profile>("profiles") ?: listOf(
            Profile(
                id = 0,
                name = context.resources.getString(R.string.mode_day_title),
                symbolColor = WidgetColor.GREEN,
                ringColor = WidgetColor.GREEN,
                symbol = WidgetSymbol.DAY,
                guideVibration = true,
                vibrationOn = true,
                guideVolume = true,
                volumeMode = VolumeMode.ALOUD,
                useCommonVolume = true,
                ringVolume = 100,
                notificationVolume = 100,
                mediaVolume = 100,
                alarmVolume = 100,
                guideScreenTimeout = false,
                screenTimeout = 120,
                guideScreenBrightness = false,
                screenBrightness = -1,

            ),
            Profile(
                id = 1,
                name = context.resources.getString(R.string.mode_night_title),
                symbolColor = WidgetColor.BLUE,
                ringColor = WidgetColor.BLUE,
                symbol = WidgetSymbol.NIGHT,
                guideVibration = true,
                vibrationOn = true,
                guideVolume = true,
                volumeMode = VolumeMode.SILENCE,
                useCommonVolume = true,
                ringVolume = 0,
                notificationVolume = 0,
                mediaVolume = 0,
                alarmVolume = 0,
                guideScreenTimeout = false,
                screenTimeout = 120,
                guideScreenBrightness = false,
                screenBrightness = -1
            ),
            Profile(
                id = 1,
                name = context.resources.getString(R.string.mode_night_title),
                symbolColor = WidgetColor.BLUE,
                ringColor = WidgetColor.BLUE,
                symbol = WidgetSymbol.NIGHT,
                guideVibration = true,
                vibrationOn = true,
                guideVolume = true,
                volumeMode = VolumeMode.SILENCE,
                useCommonVolume = true,
                ringVolume = 0,
                notificationVolume = 0,
                mediaVolume = 0,
                alarmVolume = 0,
                guideScreenTimeout = false,
                screenTimeout = 120,
                guideScreenBrightness = false,
                screenBrightness = -1
            )
        ),
        onValueChange =
            {
                scope.withMutex { putList("profiles", it) }
            }
    )

    override val currentProfile = stateFlow(
        initialValue = getInt("currentProfile") ?: 0,
        onValueChange = {
            scope.withMutex { putInt("currentProfile", it) }
        }
    )
}