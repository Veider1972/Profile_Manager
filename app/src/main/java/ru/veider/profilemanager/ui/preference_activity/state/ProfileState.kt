package ru.veider.profilemanager.ui.preference_activity.state

import android.net.Uri
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetSymbol
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetSymbolColor

data class ProfileState(
    val id: Long = 0,
    var name: String = "День",
    var symbolColor: WidgetSymbolColor = WidgetSymbolColor.GREEN,
    var ringColor: WidgetSymbolColor = WidgetSymbolColor.GREEN,
    var symbol: WidgetSymbol = WidgetSymbol.DAY,
    var guideVibration: Boolean = false,
    var vibrationOn: Boolean = true,
    var guideVolume: Boolean = false,
    var commonVolume: Int = 100,
    var useCommonVolume: Boolean = false,
    var notificationVolume: Int = 100,
    var mediaVolume: Int = 100,
    var alarmVolume: Int = 100,
    var guideCallMelody: Boolean = false,
    var callMelody: Uri = Uri.EMPTY,
    var guideSMSMelody: Boolean = false,
    var smsMelody: Uri = Uri.EMPTY,
    var guideAlarmMelody: Boolean = false,
    var alarmMelody: Uri = Uri.EMPTY,
    var guideWiFi: Boolean = false,
    var wiFiOn: Boolean = false,
    var guideMobileData: Boolean = false,
    var mobileDataOn: Boolean = false,
    var guideBluetooth: Boolean = false,
    var bluetoothOn: Boolean = false,
    var guideAccessPoint: Boolean = false,
    var accessPointOn: Boolean = false,
    var guideScreenTimeout: Boolean = false,
    var screenTimeout: Int = 15,
    var guideScreenBrightness: Boolean = false,
    var screenBrightness: Int = 15,
    var guideSync: Boolean = false,
    var syncOn: Boolean = false,
    var guideScreenRotation: Boolean = false,
    var screenRotationOn: Boolean = false,
    var guideScreenLock: Boolean = false,
    var screenLockOn: Boolean = false
)