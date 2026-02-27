package ru.veider.profilemanager.ui.preference_activity.state

import android.net.Uri
import ru.veider.profilemanager.domain.preference.VolumeMode
import ru.veider.profilemanager.domain.preference.WidgetSymbol
import ru.veider.profilemanager.domain.preference.WidgetColor

data class ProfileState(
    val id: Long = 0,
    val name: String = "День",
    val symbolColor: WidgetColor = WidgetColor.GREEN,
    val ringColor: WidgetColor = WidgetColor.GREEN,
    val symbol: WidgetSymbol = WidgetSymbol.DAY,
    val guideVibration: Boolean = true,
    val vibrationOn: Boolean = true,
    val guideVolume: Boolean = true,
    val useCommonVolume: Boolean = true,
    val volumeMode: VolumeMode = VolumeMode.ALOUD,
    val ringVolume: Int = 100,
    val useRingVolume: Boolean = false,
    val notificationVolume: Int = 100,
    val mediaVolume: Int = 100,
    val alarmVolume: Int = 100,
    val guideScreenTimeout: Boolean = false,
    val screenTimeout: Int = 15,
    val guideScreenBrightness: Boolean = false,
    val screenBrightness: Int = 15
)