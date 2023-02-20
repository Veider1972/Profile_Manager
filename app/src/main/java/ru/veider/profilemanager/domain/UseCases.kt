package ru.veider.profilemanager.domain

import android.net.Uri
import ru.veider.profilemanager.repo.localrepo.entity.ProfileData
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData
import ru.veider.profilemanager.ui.preference_activity.assets.enums.*
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState

interface UseCases {
    suspend fun getCurrentProfile(): Resource<Long>
    fun putCurrentProfile(currentProfileId:Long)

    suspend fun getWidgetSettings(): Resource<WidgetState?>
    fun putWidgetSettings(widgetSettings: WidgetState)
    fun updateWidgetSettings(widgetSettings: WidgetState)

    suspend fun getProfiles(): Resource<List<ProfileState>>
    fun putProfile(profile: ProfileState)
    fun updateProfile(profile: ProfileState)
    fun deleteProfile(profile: ProfileState)
}