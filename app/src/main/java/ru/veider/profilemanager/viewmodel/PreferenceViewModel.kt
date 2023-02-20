package ru.veider.profilemanager.viewmodel

import android.annotation.TargetApi
import android.app.Application
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.ComposeCompilerApi
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.veider.profilemanager.BuildConfig
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.Resource
import ru.veider.profilemanager.domain.UseCases
import ru.veider.profilemanager.ui.preference_activity.assets.enums.*
import ru.veider.profilemanager.ui.preference_activity.assets.toInt
import ru.veider.profilemanager.ui.preference_activity.state.ProfileListState
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState
import ru.veider.profilemanager.ui.widget.assets.*

class PreferenceViewModel(
    private val app: Application,
    private val useCases: UseCases
) : ViewModel() {

    private val _profilesState = MutableStateFlow(ProfileListState())
    val profilesState: StateFlow<ProfileListState> get() = _profilesState.asStateFlow()

    private val _widgetState = MutableStateFlow(WidgetState())
    val widgetState: StateFlow<WidgetState> = _widgetState.asStateFlow()

    private val _currentProfileIdState = MutableStateFlow(-1L)
    val currentProfileIdState: StateFlow<Long> = _currentProfileIdState.asStateFlow()

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val getProfiles = useCases.getProfiles()) {
                is Resource.Success -> {
                    getProfiles.data?.run {
                        if (this.isNotEmpty()) {
                            _profilesState.update {
                                it.copy(
                                    data = this.toMutableList()
                                )
                            }
                        } else {
                            val dayProfile = ProfileState(
                                id = 0,
                                name = app.resources.getString(R.string.mode_day_title),
                                symbolColor = WidgetColor.GREEN, ringColor = WidgetColor.GREEN, symbol = WidgetSymbol.DAY,
                                guideVibration = true, vibrationOn = true,
                                guideVolume = true, commonVolume = 100, useCommonVolume = true, notificationVolume = 100, mediaVolume = 100,
                                alarmVolume = 100,
                                guideCallMelody = false, callMelody = Uri.EMPTY,
                                guideSMSMelody = false, smsMelody = Uri.EMPTY,
                                guideAlarmMelody = false, alarmMelody = Uri.EMPTY,
                                guideWiFi = false, wiFiOn = false,
                                guideMobileData = false, mobileDataOn = false,
                                guideBluetooth = false, bluetoothOn = false,
                                guideAccessPoint = false, accessPointOn = false,
                                guideScreenTimeout = false, screenTimeout = 120,
                                guideScreenBrightness = false, screenBrightness = -1,
                                guideSync = false, syncOn = false,
                                guideScreenRotation = false, screenRotationOn = false,
                                guideScreenLock = false, screenLockOn = false
                            )
                            val nightProfile = ProfileState(
                                id = 1,
                                name = app.resources.getString(R.string.mode_night_title),
                                symbolColor = WidgetColor.BLUE, ringColor = WidgetColor.BLUE, symbol = WidgetSymbol.NIGHT,
                                guideVibration = true, vibrationOn = true,
                                guideVolume = true, commonVolume = 0, useCommonVolume = true, notificationVolume = 0, mediaVolume = 0,
                                alarmVolume = 0,
                                guideCallMelody = false, callMelody = Uri.EMPTY,
                                guideSMSMelody = false, smsMelody = Uri.EMPTY,
                                guideAlarmMelody = false, alarmMelody = Uri.EMPTY,
                                guideWiFi = false, wiFiOn = false,
                                guideMobileData = false, mobileDataOn = false,
                                guideBluetooth = false, bluetoothOn = false,
                                guideAccessPoint = false, accessPointOn = false,
                                guideScreenTimeout = false, screenTimeout = 120,
                                guideScreenBrightness = false, screenBrightness = -1,
                                guideSync = false, syncOn = false,
                                guideScreenRotation = false, screenRotationOn = false,
                                guideScreenLock = false, screenLockOn = false
                            )
                            viewModelScope.launch {
                                useCases.putProfile(dayProfile)
                                useCases.putProfile(nightProfile)
                            }
                            _profilesState.update {
                                it.copy(
                                    data = mutableListOf(dayProfile, nightProfile)
                                )
                            }
                        }
                    }
                }
                is Resource.Error   -> {

                }
            }
            when (val getCurrentProfileId = useCases.getCurrentProfile()) {
                is Resource.Success -> {
                    if (getCurrentProfileId.data != null) {
                        _currentProfileIdState.value = getCurrentProfileId.data
                    } else {
                        _currentProfileIdState.value = 0L
                        viewModelScope.launch {
                            useCases.putCurrentProfile(0)
                        }
                    }
                }
                is Resource.Error   -> {
                    _currentProfileIdState.value = -1
                }
            }
            when (val getWidgetState = useCases.getWidgetSettings()) {
                is Resource.Success -> {
                    if (getWidgetState.data != null) {
                        getWidgetState.data.run {
                            _widgetState.update {
                                it.copy(
                                    notificationType = this.notificationType,
                                    backgroundColor = this.backgroundColor,
                                    backgroundTransparency = this.backgroundTransparency,
                                    notificationOn = this.notificationOn
                                )
                            }
                        }
                    } else {
                        val widget = WidgetState(
                            notificationType = WidgetNotificationType.FULL,
                            backgroundTransparency = WidgetBackgroundTransparency(0),
                            backgroundColor = WidgetBackground.GRAY,
                            notificationOn = false
                        )
                        _widgetState.update {
                            it.copy(
                                notificationType = widget.notificationType,
                                backgroundTransparency = widget.backgroundTransparency,
                                backgroundColor = widget.backgroundColor,
                                notificationOn = widget.notificationOn
                            )
                        }
                        viewModelScope.launch {
                            useCases.putWidgetSettings(widget)
                        }
                    }
                }
                is Resource.Error   -> {}
            }
        }
    }

    fun setProfileState(id: Long) {
        profilesState.value.run {
            val pos = this.pos(id)
            Log.d("TAG", "setProfileState $id $pos")
            this.data[pos].run {
                _profileState.update {
                    it.copy(
                        id = this.id,
                        name = this.name,
                        symbolColor = this.symbolColor,
                        ringColor = this.ringColor,
                        symbol = this.symbol,
                        guideVibration = this.guideVibration,
                        vibrationOn = this.vibrationOn,
                        guideVolume = this.guideVolume,
                        commonVolume = this.commonVolume,
                        useCommonVolume = this.useCommonVolume,
                        notificationVolume = this.notificationVolume,
                        mediaVolume = this.mediaVolume,
                        alarmVolume = this.alarmVolume,
                        guideCallMelody = this.guideCallMelody,
                        callMelody = this.callMelody,
                        guideSMSMelody = this.guideSMSMelody,
                        smsMelody = this.smsMelody,
                        guideAlarmMelody = this.guideAlarmMelody,
                        alarmMelody = this.alarmMelody,
                        guideWiFi = this.guideWiFi,
                        wiFiOn = this.wiFiOn,
                        guideMobileData = this.guideMobileData,
                        mobileDataOn = this.mobileDataOn,
                        guideBluetooth = this.guideBluetooth,
                        bluetoothOn = this.bluetoothOn,
                        guideAccessPoint = this.guideAccessPoint,
                        accessPointOn = this.accessPointOn,
                        guideScreenTimeout = this.guideScreenTimeout,
                        screenTimeout = this.screenTimeout,
                        guideScreenBrightness = this.guideScreenBrightness,
                        screenBrightness = this.screenBrightness,
                        guideSync = this.guideSync,
                        syncOn = this.syncOn,
                        guideScreenRotation = this.guideScreenRotation,
                        screenRotationOn = this.screenRotationOn,
                        guideScreenLock = this.guideScreenLock,
                        screenLockOn = this.screenLockOn
                    )
                }
            }
        }
    }

    fun notificationChanged(notificationOn: Boolean) {
        _widgetState.update {
            it.copy(notificationOn = notificationOn)
        }
        useCases.updateWidgetSettings(widgetState.value)
    }

    fun setNotificationType(notificationType: WidgetNotificationType) {
        _widgetState.update {
            it.copy(notificationType = notificationType)
        }
        useCases.updateWidgetSettings(widgetState.value)
    }

    fun setBackgroundColor(backgroundColor: WidgetBackground) {
        _widgetState.update {
            it.copy(backgroundColor = backgroundColor)
        }
        useCases.updateWidgetSettings(widgetState.value)
    }

    fun setBackgroundTransparency(backgroundTransparency: WidgetBackgroundTransparency) {
        _widgetState.update {
            it.copy(backgroundTransparency = backgroundTransparency)
        }
        useCases.updateWidgetSettings(widgetState.value)
    }

    fun setCurrentProfile(profileState: ProfileState) {
        _currentProfileIdState.value = profileState.id
        useCases.putCurrentProfile(profileState.id)
        app.getSharedPreferences(PROFILE_MANAGER, MODE_PRIVATE)?.run {
            edit().apply {
                putInt(WIDGET_BACKGROUND, widgetState.value.backgroundColor.ordinal)
                putInt(WIDGET_TRANSPARENCY, widgetState.value.backgroundTransparency.value)
                putInt(WIDGET_RING_COLOR, profileState.ringColor.ordinal)
                putInt(WIDGET_SYMBOL, profileState.symbol.ordinal)
                putInt(WIDGET_SYMBOL_COLOR, profileState.symbolColor.ordinal)
                putString(WIDGET_NAME, profileState.name)
                apply()
            }
        }
        val updateIntent = Intent().apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        }
        app.sendBroadcast(updateIntent)
        setPhoneProfile(profileState)
    }

    @TargetApi(28)
    fun setPhoneProfile(profileState: ProfileState) {
        if (profileState.guideVibration) {

        }
        if (profileState.guideVolume) {

        }
        if (profileState.guideCallMelody) {

        }
        if (profileState.guideSMSMelody) {

        }
        if (profileState.guideAlarmMelody) {

        }
        if (profileState.guideWiFi) {
            val wifiManager = app.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiManager.isWifiEnabled = false
//            ProfileUtils.setWifi(profileState.wiFiOn, app)
        }
        if (profileState.guideMobileData) {

        }
        if (profileState.guideBluetooth) {

        }
        if (profileState.guideAccessPoint) {

        }
        if (profileState.guideScreenTimeout) {

            Settings.System.putInt(app.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT,
                                   if (profileState.screenTimeout == 0) Int.MAX_VALUE else profileState.screenTimeout * 1000
            )
        }
        if (profileState.guideScreenBrightness) {
            if (profileState.screenBrightness < 0) {
                Settings.System.putInt(app.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
            } else {
                Settings.System.putInt(app.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
                Settings.System.putInt(app.contentResolver, Settings.System.SCREEN_BRIGHTNESS, (profileState.screenBrightness * 2.55).toInt())
            }

        }
        if (profileState.guideSync) {

        }
        if (profileState.guideScreenRotation) {
            Settings.System.putInt(app.contentResolver, Settings.System.ACCELEROMETER_ROTATION, if (profileState.screenRotationOn) 1 else 0)
        }
        if (profileState.guideScreenLock) {
        }
    }



    fun updateWidget() {
        app.getSharedPreferences(PROFILE_MANAGER, MODE_PRIVATE)?.run {
            edit().apply {
                putInt(WIDGET_BACKGROUND, widgetState.value.backgroundColor.ordinal)
                putInt(WIDGET_TRANSPARENCY, widgetState.value.backgroundTransparency.value)
                apply()
            }
        }
        useCases.putWidgetSettings(widgetState.value)
        val updateIntent = Intent().apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        }
        app.sendBroadcast(updateIntent)
    }

    private val pos get() = profilesState.value.pos(profileState.value.id)

    fun setProfileGuideVibration(guideVibration: Boolean) {

        _profileState.update {
            it.copy(guideVibration = guideVibration)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideVibration = guideVibration
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileVibration(vibrationOn: Boolean) {
        _profileState.update {
            it.copy(vibrationOn = vibrationOn)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].vibrationOn = vibrationOn
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideVolume(guideVolume: Boolean) {
        _profileState.update {
            it.copy(guideVolume = guideVolume)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideVolume = guideVolume
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileVolume(commonVolume: Int, useCommonVolume: Boolean, notificationVolume: Int, mediaVolume: Int, alarmVolume: Int) {
        _profileState.update {
            it.copy(commonVolume = commonVolume,
                    useCommonVolume = useCommonVolume,
                    notificationVolume = notificationVolume,
                    mediaVolume = mediaVolume,
                    alarmVolume = alarmVolume
            )
        }
        _profilesState.update {
            it.apply {
                with(this.data[pos]) {
                    this.commonVolume = commonVolume
                    this.useCommonVolume = useCommonVolume
                    this.notificationVolume = notificationVolume
                    this.mediaVolume = mediaVolume
                    this.alarmVolume = alarmVolume
                }
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideCallMelody(guideCallMelody: Boolean) {
        _profileState.update {
            it.copy(guideCallMelody = guideCallMelody)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideCallMelody = guideCallMelody
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileCallMelody(callMelody: Uri) {
        _profileState.update {
            it.copy(callMelody = callMelody)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].callMelody = callMelody
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideSMSMelody(guideSMSMelody: Boolean) {
        _profileState.update {
            it.copy(guideSMSMelody = guideSMSMelody)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideSMSMelody = guideSMSMelody
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileSMSMelody(smsMelody: Uri) {
        _profileState.update {
            it.copy(smsMelody = smsMelody)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].smsMelody = smsMelody
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideAlarmMelody(guideAlarmMelody: Boolean) {
        _profileState.update {
            it.copy(guideAlarmMelody = guideAlarmMelody)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideAlarmMelody = guideAlarmMelody
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileAlarmMelody(alarmMelody: Uri) {
        _profileState.update {
            it.copy(alarmMelody = alarmMelody)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].alarmMelody = alarmMelody
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideWiFi(guideWiFi: Boolean) {
        _profileState.update {
            it.copy(guideWiFi = guideWiFi)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideWiFi = guideWiFi
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileWiFi(wiFiOn: Boolean) {
        _profileState.update {
            it.copy(wiFiOn = wiFiOn)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].wiFiOn = wiFiOn
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideMobileData(guideMobileData: Boolean) {
        _profileState.update {
            it.copy(guideMobileData = guideMobileData)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideMobileData = guideMobileData
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileMobileData(mobileDataOn: Boolean) {
        _profileState.update {
            it.copy(mobileDataOn = mobileDataOn)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].mobileDataOn = mobileDataOn
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideBluetooth(guideBluetooth: Boolean) {
        _profileState.update {
            it.copy(guideBluetooth = guideBluetooth)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideBluetooth = guideBluetooth
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileBluetooth(bluetoothOn: Boolean) {
        _profileState.update {
            it.copy(bluetoothOn = bluetoothOn)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].bluetoothOn = bluetoothOn
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideAccessPoint(guideAccessPoint: Boolean) {
        _profileState.update {
            it.copy(guideAccessPoint = guideAccessPoint)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideAccessPoint = guideAccessPoint
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileAccessPoint(accessPointOn: Boolean) {
        _profileState.update {
            it.copy(accessPointOn = accessPointOn)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].accessPointOn = accessPointOn
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideScreenTimeOut(guideScreenTimeout: Boolean) {
        _profileState.update {
            it.copy(guideScreenTimeout = guideScreenTimeout)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideScreenTimeout = guideScreenTimeout
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileScreenTimeOut(screenTimeout: Int) {
        _profileState.update {
            it.copy(screenTimeout = screenTimeout)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].screenTimeout = screenTimeout
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideScreenBrightness(guideScreenBrightness: Boolean) {
        _profileState.update {
            it.copy(guideScreenBrightness = guideScreenBrightness)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideScreenBrightness = guideScreenBrightness
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileScreenBrightness(screenBrightness: Int) {
        _profileState.update {
            it.copy(screenBrightness = screenBrightness)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].screenBrightness = screenBrightness
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideSync(guideSync: Boolean) {
        _profileState.update {
            it.copy(guideSync = guideSync)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideSync = guideSync
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileSync(syncOn: Boolean) {
        _profileState.update {
            it.copy(syncOn = syncOn)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].syncOn = syncOn
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideScreenRotation(guideScreenRotation: Boolean) {
        _profileState.update {
            it.copy(guideScreenRotation = guideScreenRotation)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideScreenRotation = guideScreenRotation
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileScreenRotation(screenRotationOn: Boolean) {
        _profileState.update {
            it.copy(screenRotationOn = screenRotationOn)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].screenRotationOn = screenRotationOn
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileGuideScreenLock(guideScreenLock: Boolean) {
        _profileState.update {
            it.copy(guideScreenLock = guideScreenLock)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideScreenLock = guideScreenLock
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileScreenLock(screenLockOn: Boolean) {
        _profileState.update {
            it.copy(screenLockOn = screenLockOn)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].screenLockOn = screenLockOn
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileName(name: String) {
        _profileState.update {
            it.copy(name = name)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].name = name
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileRingColor(ringColor: WidgetColor) {
        _profileState.update {
            it.copy(ringColor = ringColor)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].ringColor = ringColor
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileSymbolColor(symbolColor: WidgetColor) {
        _profileState.update {
            it.copy(symbolColor = symbolColor)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].symbolColor = symbolColor
            }
        }
        useCases.updateProfile(profileState.value)
    }

    fun setProfileSymbol(symbol: WidgetSymbol) {
        _profileState.update {
            it.copy(symbol = symbol)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].symbol = symbol
            }
        }
        useCases.updateProfile(profileState.value)
    }
}