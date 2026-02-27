package ru.veider.profilemanager.repo

import kotlinx.coroutines.flow.MutableStateFlow
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState

interface Preference {
    val widget: MutableStateFlow<WidgetState>
    val profiles: MutableStateFlow<List<Profile>>
    val currentProfile: MutableStateFlow<Int>
}