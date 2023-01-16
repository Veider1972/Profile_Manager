package ru.veider.profilemanager.ui.preference_activity.state

import android.net.Uri
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetSymbol
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetSymbolColor

data class ProfileSettingsState(
    val name: String = "День",
    val symbolColor: WidgetSymbolColor = WidgetSymbolColor.GREEN,
    val ringColor: WidgetSymbolColor = WidgetSymbolColor.GREEN,
    val symbol: WidgetSymbol = WidgetSymbol.DAY,
    val isGuideVibration: Boolean = false,
    val isVibration: Boolean = true,
    val isGuideVolume: Boolean = false,
    val isVolume: Boolean = true,
    val commonVolume: Int = 100,
    val isCommonVolume: Boolean = false,
    val notificationVolume: Int = 100,
    val mediaVolume: Int = 100,
    val alarmVolume: Int = 100,
    val isGuideCallMelody: Boolean = false,
    val callMelody: Uri = Uri.EMPTY,
    val isGuideSMSMelody: Boolean = false,
    val smsMelody: Uri = Uri.EMPTY,
    val isGuideWiFi: Boolean = false,
    val isWiFi: Boolean = false,
    val isGuideMobileData: Boolean = false,
    val isMobileData: Boolean = false,
    val isGuideBluetooth: Boolean = false,
    val isBluetooth: Boolean = false,
    val isGuideAccessPoint: Boolean = false,
    val isAccessPoint: Boolean = false,
    val isGuideScreenTimeout: Boolean = false,
    val screenTimeout: Int = 15,
    val isGuideScreenBrightness: Boolean = false,
    val screenBrightness: Int = 15,
    val isGuideSync: Boolean = false,
    val isSync: Boolean = false,
    val isGuideScreenRotation: Boolean = false,
    val isScreenRotation: Boolean = false,
    val isGuideScreenLock: Boolean = false,
    val isScreenLock: Boolean = false
)