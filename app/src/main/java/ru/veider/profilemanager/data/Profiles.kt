package ru.veider.profilemanager.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.*
import kotlin.math.roundToInt

val aloudProfiles = listOf(ProfileType.Day, ProfileType.Night, ProfileType.Muted, ProfileType.Quiet, ProfileType.Aloud)
val silentProfiles = listOf(ProfileType.Silent, ProfileType.Vibro)

fun dayProfile(context: Context) =
    Profile(
        type = ProfileType.Day,
        name = context.resources.getString(R.string.mode_day_title),
        color = Color(context.getColor(R.color.widget_color_green)),
        symbol = Symbol.Day,
        vibration = true,
        useCommonVolume = true,
        ringVolume = PhoneCapabilities(context).maxRingVolume,
        notificationVolume = PhoneCapabilities(context).maxNotificationVolume,
        mediaVolume = PhoneCapabilities(context).maxMusicVolume,
        alarmVolume = PhoneCapabilities(context).maxAlarmVolume,
        guideBrightness = true,
        brightness = null
    )

fun nightProfile(context: Context) =
    Profile(
        type = ProfileType.Night,
        name = context.resources.getString(R.string.mode_night_title),
        color = Color(context.getColor(R.color.widget_color_blue)),
        symbol = Symbol.Night,
        vibration = true,
        useCommonVolume = true,
        ringVolume = 0,
        notificationVolume = 0,
        mediaVolume = 0,
        alarmVolume = 0,
        guideBrightness = true,
        brightness = 1
    )

fun vibroProfile(context: Context) =
    Profile(
        type = ProfileType.Vibro,
        name = context.resources.getString(R.string.mode_vibro_title),
        color = Color(context.getColor(R.color.widget_color_light_blue)),
        symbol = Symbol.Vibro,
        vibration = true,
        useCommonVolume = true,
        ringVolume = 0,
        notificationVolume = 0,
        mediaVolume = 0,
        alarmVolume = 0,
        guideBrightness = true,
        brightness = null
    )

fun silentProfile(context: Context) =
    Profile(
        type = ProfileType.Silent,
        name = context.resources.getString(R.string.mode_silent_title),
        color = Color(context.getColor(R.color.widget_color_orange)),
        symbol = Symbol.Silent,
        vibration = false,
        useCommonVolume = true,
        ringVolume = 0,
        notificationVolume = 0,
        mediaVolume = 0,
        alarmVolume = 0,
        guideBrightness = true,
        brightness = null
    )

fun quietProfile(context: Context) =
    Profile(
        type = ProfileType.Quiet,
        name = context.resources.getString(R.string.mode_quiet_title),
        color = Color(context.getColor(R.color.widget_color_purple)),
        symbol = Symbol.Quiet,
        vibration = true,
        useCommonVolume = true,
        ringVolume = (PhoneCapabilities(context).maxRingVolume*0.2).roundToInt(),
        notificationVolume = (PhoneCapabilities(context).maxNotificationVolume*0.2).roundToInt(),
        mediaVolume = (PhoneCapabilities(context).maxMusicVolume*0.2).roundToInt(),
        alarmVolume = (PhoneCapabilities(context).maxAlarmVolume*0.2).roundToInt(),
        guideBrightness = true,
        brightness = null
    )

fun mutedProfile(context: Context) =
    Profile(
        type = ProfileType.Muted,
        name = context.resources.getString(R.string.mode_muted_title),
        color = Color(context.getColor(R.color.widget_color_purple)),
        symbol = Symbol.Muted,
        vibration = true,
        useCommonVolume = true,
        ringVolume = (PhoneCapabilities(context).maxRingVolume*0.6).roundToInt(),
        notificationVolume = (PhoneCapabilities(context).maxNotificationVolume*0.6).roundToInt(),
        mediaVolume = (PhoneCapabilities(context).maxMusicVolume*0.6).roundToInt(),
        alarmVolume = (PhoneCapabilities(context).maxAlarmVolume*0.6).roundToInt(),
        guideBrightness = true,
        brightness = null
    )

fun aloudProfile(context: Context) =
    Profile(
        type = ProfileType.Aloud,
        name = context.resources.getString(R.string.mode_aloud_title),
        color = Color(context.getColor(R.color.widget_color_purple)),
        symbol = Symbol.Aloud,
        vibration = true,
        useCommonVolume = true,
        ringVolume = PhoneCapabilities(context).maxRingVolume,
        notificationVolume = PhoneCapabilities(context).maxNotificationVolume,
        mediaVolume = PhoneCapabilities(context).maxMusicVolume,
        alarmVolume = PhoneCapabilities(context).maxAlarmVolume,
        guideBrightness = true,
        brightness = null
    )

fun defaultProfiles(context: Context): List<Profile> =
    listOf(
        dayProfile(context),
        nightProfile(context),
        vibroProfile(context),
        silentProfile(context),
        quietProfile(context),
        mutedProfile(context),
        aloudProfile(context)
    )