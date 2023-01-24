package ru.veider.profilemanager.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.Resource
import ru.veider.profilemanager.domain.UseCases
import ru.veider.profilemanager.ui.preference_activity.assets.enums.*
import ru.veider.profilemanager.ui.preference_activity.state.ProfileListState
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState

class PreferenceViewModel(
    app: Application,
    private val useCases: UseCases
) : ViewModel() {

    private val _profilesState = MutableStateFlow(ProfileListState())
    val profilesState: StateFlow<ProfileListState> = _profilesState.asStateFlow()

    private val _widgetState = MutableStateFlow(WidgetState())
    val widgetState: StateFlow<WidgetState> = _widgetState.asStateFlow()

    private val _currentProfileIdState = MutableStateFlow(-1L)
    val currentProfileIdState: StateFlow<Long> = _currentProfileIdState.asStateFlow()

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState.asStateFlow()

    private val _currentProfileState = MutableStateFlow(ProfileState())
    val currentProfileState: StateFlow<ProfileState> = _currentProfileState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val getProfiles = useCases.getProfiles()) {
                is Resource.Success -> {
                    _profilesState.update {
                        it.copy(
                            data = getProfiles.data.toMutableList()
                        )
                    }
                }
                is Resource.Error   -> {
                    val dayProfile = ProfileState(
                        id = 0,
                        name = app.resources.getString(R.string.mode_day_title),
                        symbolColor = WidgetSymbolColor.GREEN, ringColor = WidgetSymbolColor.GREEN, symbol = WidgetSymbol.DAY,
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
                        symbolColor = WidgetSymbolColor.BLUE, ringColor = WidgetSymbolColor.BLUE, symbol = WidgetSymbol.NIGHT,
                        guideVibration = true, vibrationOn = true,
                        guideVolume = true, commonVolume = 0, useCommonVolume = true, notificationVolume = 0, mediaVolume = 0, alarmVolume = 0,
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
            when (val getCurrentProfileId = useCases.getCurrentProfile()) {
                is Resource.Success -> {
                    getCurrentProfileId.data.run {
                        _currentProfileIdState.value = this
                    }
                }
                is Resource.Error   -> {
                    _currentProfileIdState.value = -1
                    viewModelScope.launch {
                        useCases.putCurrentProfile(0)
                    }
                }
            }
            when (val getWidgetState = useCases.getWidgetSettings()) {
                is Resource.Success -> {
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
                }
                is Resource.Error   -> {
                    val widget = WidgetState(
                        notificationType = WidgetNotificationType.FULL,
                        backgroundTransparency = WidgetBackgroundTransparency(0),
                        backgroundColor = WidgetBackgroundColor.GREY,
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
            currentProfileIdState.value.run {
                setCurrentProfileState(this)
            }
        }
    }


    private fun setCurrentProfileState(id: Long) {
        profilesState.value.run {
            this.data[this.pos(id)].run {
                _currentProfileState.update {
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
        viewModelScope.launch {
            useCases.updateWidgetNotification(notificationOn)
        }
    }

    fun setNotificationType(notificationType: WidgetNotificationType) {
        _widgetState.update {
            it.copy(notificationType = notificationType)
        }
        viewModelScope.launch {
            useCases.updateWidgetNotificationType(notificationType)
        }
    }

    fun setBackgroundColor(backgroundColor: WidgetBackgroundColor) {
        _widgetState.update {
            it.copy(backgroundColor = backgroundColor)
        }
        viewModelScope.launch {
            useCases.updateWidgetBackgroundColor(backgroundColor)
        }
    }

    fun setBackgroundTransparency(backgroundTransparency: WidgetBackgroundTransparency) {
        _widgetState.update {
            it.copy(backgroundTransparency = backgroundTransparency)
        }
        viewModelScope.launch {
            useCases.updateWidgetBackgroundTransparency(backgroundTransparency)
        }
    }

//    fun setProfile(widgetMode: WidgetMode) {
//
//    }

    private val pos get()= profilesState.value.pos(profileState.value.id)

    fun setProfileGuideVibration(guideVibration: Boolean) {

        _profileState.update {
            it.copy(guideVibration = guideVibration)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].guideVibration = guideVibration
            }
        }
        viewModelScope.launch {
            useCases.updateVibration(guideVibration, profileState.value.vibrationOn, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateVibration(true, vibrationOn, profileState.value.id)
        }

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
        viewModelScope.launch {
            profileState.value.apply {
                useCases.updateVolume(guideVolume, commonVolume, useCommonVolume, notificationVolume, mediaVolume, alarmVolume, id)
            }

        }
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
        viewModelScope.launch {
            useCases.updateVolume(true, commonVolume, useCommonVolume, notificationVolume, mediaVolume,
                                  alarmVolume, profileState.value.id
            )
        }
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
        viewModelScope.launch {
            useCases.updateCallMelody(guideCallMelody, profileState.value.callMelody, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateCallMelody(true, callMelody, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateSMSMelody(guideSMSMelody, profileState.value.smsMelody, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateSMSMelody(true, smsMelody, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateAlarmMelody(guideAlarmMelody, profileState.value.alarmMelody, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateAlarmMelody(true, alarmMelody, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateWifi(guideWiFi, profileState.value.wiFiOn, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateWifi(true, wiFiOn, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateMobileData(guideMobileData, profileState.value.mobileDataOn, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateMobileData(true, mobileDataOn, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateBluetooth(guideBluetooth, profileState.value.bluetoothOn, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateBluetooth(true, bluetoothOn, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateAccessPoint(guideAccessPoint, profileState.value.accessPointOn, profileState.value.id)
        }
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
        viewModelScope.launch {
            useCases.updateAccessPoint(true, accessPointOn, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateScreenTimeout(guideScreenTimeout, profileState.value.screenTimeout, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateScreenTimeout(true, screenTimeout, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateScreenBrightness(guideScreenBrightness, profileState.value.screenBrightness, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateScreenBrightness(true, screenBrightness, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateSync(guideSync, profileState.value.syncOn, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateSync(true, syncOn, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateScreenRotation(guideScreenRotation, profileState.value.screenRotationOn, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateScreenRotation(true, screenRotationOn, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateScreenLock(guideScreenLock, profileState.value.screenLockOn, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateScreenLock(true, screenLockOn, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateName(name, profileState.value.id)
        }
    }

    fun setProfileRingColor(ringColor: WidgetSymbolColor) {
        _profileState.update {
            it.copy(ringColor = ringColor)
        }
        _profilesState.update {
            it.apply {
                this.data[pos].ringColor = ringColor
            }
        }
        viewModelScope.launch {
                useCases.updateRingColor(ringColor, profileState.value.id)
        }
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
        viewModelScope.launch {
                useCases.updateSymbol(symbol, profileState.value.id)
        }
    }
}