package ru.veider.profilemanager.domain

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ru.veider.profilemanager.domain.preference.VolumeMode
import ru.veider.profilemanager.domain.preference.WidgetColor
import ru.veider.profilemanager.domain.preference.WidgetSymbol


@Keep
data class Profile(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("symbolColor") val symbolColor: WidgetColor,
        @SerializedName("ringColor") val ringColor: WidgetColor,
        @SerializedName("symbol") val symbol: WidgetSymbol,
//        @SerializedName("widgetBackground") val widgetBackground: WidgetBackground,
        @SerializedName("guideVibration") val guideVibration: Boolean,
        @SerializedName("vibrationOn") val vibrationOn: Boolean,
        @SerializedName("guideVolume") val guideVolume: Boolean,
        @SerializedName("volumeMode") val volumeMode: VolumeMode,
        @SerializedName("useCommonVolume") val useCommonVolume: Boolean,
        @SerializedName("ringVolume") val ringVolume: Int,
        @SerializedName("notificationVolume") val notificationVolume: Int,
        @SerializedName("mediaVolume") val mediaVolume: Int,
        @SerializedName("alarmVolume") val alarmVolume: Int,
        @SerializedName("guideScreenTimeout") val guideScreenTimeout: Boolean,
        @SerializedName("screenTimeout") val screenTimeout: Int,
        @SerializedName("guideScreenBrightness") val guideScreenBrightness: Boolean,
        @SerializedName("screenBrightness") val screenBrightness: Int
)
