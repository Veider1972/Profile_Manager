package ru.veider.profilemanager.repo.localrepo

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.veider.profilemanager.repo.localrepo.entity.CurrentProfileData
import ru.veider.profilemanager.repo.localrepo.entity.WidgetData
import ru.veider.profilemanager.repo.localrepo.entity.ProfileData

@Database(entities = [ProfileData::class, WidgetData::class, CurrentProfileData::class], version = 1, exportSchema = false)
abstract class ProfileDatabase: RoomDatabase() {
    abstract fun profileDao():ProfileDao
}