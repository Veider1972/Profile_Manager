package ru.veider.profilemanager.domain

import android.net.Uri
import kotlinx.coroutines.*
import ru.veider.profilemanager.repo.localrepo.LocalRepo
import ru.veider.profilemanager.repo.localrepo.entity.ProfileData
import ru.veider.profilemanager.ui.preference_activity.assets.enums.*
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState

class UseCasesImpl(
    val localRepo: LocalRepo
) : UseCases {

    override suspend fun getCurrentProfile(): Resource<Long> =
            try {
                when (val profileId = localRepo.getCurrentProfile()) {
                    is Resource.Success -> Resource.Success(profileId.data)
                    is Resource.Error   -> Resource.Error(profileId.error)
                }
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override fun putCurrentProfile(currentProfileId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.putCurrentProfile(currentProfileId)
        }
    }

    override suspend fun getWidgetSettings(): Resource<WidgetState?> =
            try {
                when (val widgetData = localRepo.getWidgetSettings()) {
                    is Resource.Success -> Resource.Success(widgetData.data?.toWidgetState())
                    is Resource.Error   -> Resource.Error(widgetData.error)
                }
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override fun putWidgetSettings(widgetSettings: WidgetState) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.putWidgetSettings(widgetSettings.toWidgetData())
        }
    }

    override fun updateWidgetSettings(widgetSettings: WidgetState) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.updateWidgetSettings(widgetSettings.toWidgetData())
        }
    }

    override suspend fun getProfiles(): Resource<List<ProfileState>> =
            try {
                when (val profiles = localRepo.getProfiles()) {
                    is Resource.Success -> Resource.Success(profiles.data?.map {
                        it.toProfileSettingsState()
                    })
                    is Resource.Error   -> Resource.Error(profiles.error)
                }
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override fun putProfile(profile: ProfileState) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.putProfile(profile.toProfileData())
        }
    }

    override fun updateProfile(profile: ProfileState) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.updateProfile(profile.toProfileData())
        }
    }

    override fun deleteProfile(profile: ProfileState) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.deleteProfile(profile.toProfileData())
        }
    }
}