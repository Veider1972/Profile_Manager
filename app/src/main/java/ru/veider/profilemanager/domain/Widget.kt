package ru.veider.profilemanager.domain

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Widget(
    @SerializedName("notificationType") val notificationType: WidgetNotificationType = WidgetNotificationType.FULL,
    @SerializedName("background") val background: WidgetBackground = WidgetBackground.White,
    @SerializedName("notificationOn") var notificationOn: Boolean = false
)