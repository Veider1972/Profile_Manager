package ru.veider.profilemanager.repo.localrepo

import androidx.room.*
import ru.veider.profilemanager.repo.localrepo.entity.CurrentProfileData
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData
import ru.veider.profilemanager.repo.localrepo.entity.ProfileData

@Dao
interface ProfileDao {

    // Текущий профиль
    @Query("SELECT current_profile_id FROM current WHERE id = 0")
    suspend fun getCurrentProfile(): Long?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putCurrentProfile(currentProfile:CurrentProfileData)


    // Текущий виджет
    @Query("SELECT * FROM widget")
    suspend fun getWidgetSettings(): WidgetData?
    @Insert
    suspend fun putWidgetSettings(widgetSettings: WidgetData)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWidgetSettings(widgetSettings: WidgetData)


    // Настройки профилей
    @Query("SELECT * FROM profiles")
    suspend fun getProfiles(): List<ProfileData>
    @Insert
    suspend fun putProfile(profile: ProfileData)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfile(profile: ProfileData)
    @Delete
    suspend fun deleteProfile(profile: ProfileData)



}