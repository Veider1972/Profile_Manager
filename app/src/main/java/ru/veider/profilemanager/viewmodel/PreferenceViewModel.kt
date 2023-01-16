package ru.veider.profilemanager.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetBackgroundColor
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetBackgroundTransparency
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetNotificationType
import ru.veider.profilemanager.ui.preference_activity.state.ProfileSettingsState
import ru.veider.profilemanager.ui.preference_activity.state.ToolsState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState
import ru.veider.profilemanager.ui.widget.assets.WidgetMode

class PreferenceViewModel:ViewModel() {
    private val _toolsState = MutableStateFlow(ToolsState())
    var toolsState: StateFlow<ToolsState> = _toolsState.asStateFlow()

    private val _widgetState = MutableStateFlow(WidgetState())
    var widgetState: StateFlow<WidgetState> = _widgetState.asStateFlow()

    private val _profileSettingsState = MutableStateFlow(ProfileSettingsState())
    var profileSettingsState: StateFlow<ProfileSettingsState> = _profileSettingsState.asStateFlow()

    fun notificationChanged(checked:Boolean){
        _toolsState.update {
            it.copy(notification = checked)
        }
    }

    fun setNotificationType(notificationType: WidgetNotificationType){
        _widgetState.update {
            it.copy(notificationType = notificationType)
        }
    }

    fun setBackgroundColor(backgroundColor: WidgetBackgroundColor){
        _widgetState.update {
            it.copy(backgroundColor = backgroundColor)
        }
    }

    fun setBackgroundTransparency(backgroundTransparency: WidgetBackgroundTransparency){
        _widgetState.update {
            it.copy(backgroundTransparency = backgroundTransparency)
        }
    }

    fun setProfile(widgetMode: WidgetMode){

    }

    fun setWidgetGuideVibration(isGuideVibration:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideVibration = isGuideVibration)
        }
    }

    fun setWidgetGuideVolume(isGuideVolume:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideVolume = isGuideVolume)
        }
    }

    fun setWidgetGuideCallMelody(isGuideCallMelody:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideCallMelody = isGuideCallMelody)
        }
    }

    fun setWidgetGuideSMSMelody(isGuideSMSMelody:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideSMSMelody = isGuideSMSMelody)
        }
    }

    fun setWidgetGuideWiFi(isGuideWiFi:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideWiFi = isGuideWiFi)
        }
    }

    fun setWidgetGuideMobileData(isGuideMobileData:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideMobileData = isGuideMobileData)
        }
    }

    fun setWidgetGuideBluetooth(isGuideBluetooth:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideBluetooth = isGuideBluetooth)
        }
    }

    fun setWidgetGuideAccessPoint(isGuideAccessPoint:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideAccessPoint = isGuideAccessPoint)
        }
    }

    fun setWidgetGuideScreenTimeOut(isGuideScreenTimeOut:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideScreenTimeout = isGuideScreenTimeOut)
        }
    }

    fun setWidgetGuideScreenBrightness(isGuideScreenBrightness:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideScreenBrightness = isGuideScreenBrightness)
        }
    }

    fun setWidgetGuideSync(isGuideSync:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideSync = isGuideSync)
        }
    }

    fun setWidgetGuideScreenRotation(isGuideScreenRotation:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideScreenRotation = isGuideScreenRotation)
        }
    }

    fun setWidgetGuideScreenLock(isGuideScreenLock:Boolean){
        _profileSettingsState.update {
            it.copy(isGuideScreenLock = isGuideScreenLock)
        }
    }
}