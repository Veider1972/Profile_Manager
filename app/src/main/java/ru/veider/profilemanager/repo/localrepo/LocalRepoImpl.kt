package ru.veider.profilemanager.repo.localrepo

import ru.veider.profilemanager.repo.localrepo.entity.ProfileData
import ru.veider.profilemanager.domain.Resource
import ru.veider.profilemanager.repo.localrepo.entity.CurrentProfileData
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData

class LocalRepoImpl(
    private val db: ProfileDatabase
) : LocalRepo {

    override suspend fun getCurrentProfile(): Resource<Long?> =
            try {
                Resource.Success(db.profileDao().getCurrentProfile())
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override suspend fun putCurrentProfile(currentProfileId: Long) {
        db.profileDao().putCurrentProfile(CurrentProfileData(0, currentProfileId))
    }

    override suspend fun getWidgetSettings(): Resource<WidgetData?> =
            try {
                Resource.Success(db.profileDao().getWidgetSettings())
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override suspend fun putWidgetSettings(widgetSettings: WidgetData){
        db.profileDao().putWidgetSettings(widgetSettings)
    }

    override suspend fun updateWidgetSettings(widgetSettings: WidgetData) {
        db.profileDao().putWidgetSettings(widgetSettings)
    }

    override suspend fun getProfiles(): Resource<List<ProfileData>> =
            try {
                Resource.Success(db.profileDao().getProfiles())
            } catch (e: Exception) {
                Resource.Error(e)
            }

    override suspend fun putProfile(profile: ProfileData) {
        db.profileDao().putProfile(profile)
    }

    override suspend fun updateProfile(profile: ProfileData) {
        db.profileDao().updateProfile(profile)
    }

    override suspend fun deleteProfile(profile: ProfileData) {
        db.profileDao().deleteProfile(profile)
    }

}