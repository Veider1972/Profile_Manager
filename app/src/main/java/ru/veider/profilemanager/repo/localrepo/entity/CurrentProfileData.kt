package ru.veider.profilemanager.repo.localrepo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "current")
data class CurrentProfileData(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo(name = "current_profile_id") val currentProfileId: Long,
)