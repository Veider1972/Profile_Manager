package ru.veider.profilemanager.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.veider.profilemanager.ui.preference_activity.ToolsState
import ru.veider.profilemanager.ui.preference_activity.WidgetState
import ru.veider.profilemanager.ui.preference_activity.assets.BackgroundColor
import ru.veider.profilemanager.ui.preference_activity.assets.BackgroundTransparency
import ru.veider.profilemanager.ui.preference_activity.assets.NotificationType
import ru.veider.profilemanager.ui.widget.assets.WidgetMode

class PreferenceViewModel:ViewModel() {
    private val _toolsState = MutableStateFlow(ToolsState())
    var toolsState: StateFlow<ToolsState> = _toolsState.asStateFlow()

    private val _widgetState = MutableStateFlow(WidgetState())
    var widgetState: StateFlow<WidgetState> = _widgetState.asStateFlow()

    fun notificationChanged(checked:Boolean){
        _toolsState.update {
            it.copy(notification = checked)
        }
    }

    fun setNotificationType(notificationType: NotificationType){
        _widgetState.update {
            it.copy(notificationType = notificationType)
        }
    }

    fun setBackgroundColor(backgroundColor: BackgroundColor){
        _widgetState.update {
            it.copy(backgroundColor = backgroundColor)
        }
    }

    fun setBackgroundTransparency(backgroundTransparency:BackgroundTransparency){
        _widgetState.update {
            it.copy(backgroundTransparency = backgroundTransparency)
        }
    }

    fun setProfile(widgetMode: WidgetMode){

    }
}