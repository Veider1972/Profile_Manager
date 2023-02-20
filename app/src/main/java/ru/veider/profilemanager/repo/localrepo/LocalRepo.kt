package ru.veider.profilemanager.repo.localrepo

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.veider.profilemanager.repo.localrepo.entity.ProfileData
import ru.veider.profilemanager.domain.Resource
import ru.veider.profilemanager.repo.localrepo.entity.CurrentProfileData
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData

interface LocalRepo {
    suspend fun getCurrentProfile(): Resource<Long?>
    suspend fun putCurrentProfile(currentProfileId: Long)

    suspend fun getWidgetSettings(): Resource<WidgetData?>
    suspend fun putWidgetSettings(widgetSettings: WidgetData)
    suspend fun updateWidgetSettings(widgetSettings: WidgetData)

    suspend fun getProfiles(): Resource<List<ProfileData>>
    suspend fun putProfile(profile: ProfileData)
    suspend fun updateProfile(profile: ProfileData)
    suspend fun deleteProfile(profile: ProfileData)
}