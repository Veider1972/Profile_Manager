package ru.veider.profilemanager.domain

import androidx.annotation.Keep
import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class Profile(
        @SerializedName("type") val type: ProfileType,
        @SerializedName("name") val name: String,
        @SerializedName("color") @Contextual val color: Color,
        @SerializedName("symbol") val  symbol: Symbol,
        @SerializedName("vibration") val vibration: Boolean,
        @SerializedName("useCommonVolume") val useCommonVolume: Boolean,
        @SerializedName("ringVolume") val ringVolume: Int,
        @SerializedName("notificationVolume") val notificationVolume: Int,
        @SerializedName("mediaVolume") val mediaVolume: Int,
        @SerializedName("alarmVolume") val alarmVolume: Int,
        @SerializedName("guideScreenBrightness") val guideBrightness: Boolean,
        @SerializedName("screenBrightness") val brightness: Int?
)
