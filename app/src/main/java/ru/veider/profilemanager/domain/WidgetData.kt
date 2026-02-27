package ru.veider.profilemanager.domain

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WidgetData(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("notificationType") val notificationType: Int,
    @SerializedName("backgroundColor") val backgroundColor: Int,
    @SerializedName("backgroundTransparency") val backgroundTransparency: Int,
    @SerializedName("notification") val notification: Boolean
)