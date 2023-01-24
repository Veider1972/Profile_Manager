package ru.veider.profilemanager.repo.localrepo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "widget")
data class WidgetData(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo(name = "notification_type") val notificationType: Int,
    @ColumnInfo(name = "background_color") val backgroundColor: Int,
    @ColumnInfo(name = "background_transparency") val backgroundTransparency: Int,
    @ColumnInfo(name = "notification") val notification: Boolean
)
