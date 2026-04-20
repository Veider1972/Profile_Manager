package ru.veider.profilemanager.domain

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {
    @Serializable data object Profiles : Screen
    @Serializable data class ProfileEditor(val profile: Profile) : Screen
//    @Serializable data class WidgetEditor(val profile: Profile, val onProfileChange: (Profile) -> Unit, val widget: Widget, val onWidgetChange: (Widget) -> Unit) : Screen
//    @Serializable
//    data object About : Screen
}