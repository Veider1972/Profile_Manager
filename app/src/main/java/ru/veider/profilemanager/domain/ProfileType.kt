package ru.veider.profilemanager.domain

import android.content.Context
import ru.veider.profilemanager.R

enum class ProfileType(val value: Int) {
    Day(0),
    Night(1),
    Vibro(2),
    Silent(3),
    Quiet(4),
    Muted(5),
    Aloud(6);
    fun getName(context: Context) =
        when (this){
            ProfileType.Day -> context.getString(R.string.mode_day_title)
            ProfileType.Night -> context.getString(R.string.mode_night_title)
            ProfileType.Vibro -> context.getString(R.string.mode_vibro_title)
            ProfileType.Silent -> context.getString(R.string.mode_silent_title)
            ProfileType.Quiet -> context.getString(R.string.mode_quiet_title)
            ProfileType.Muted -> context.getString(R.string.mode_muted_title)
            ProfileType.Aloud -> context.getString(R.string.mode_aloud_title)
        }
}

fun Int.toProfileType() =
    when (this){
        ProfileType.Day.value -> ProfileType.Day
        ProfileType.Night.value -> ProfileType.Night
        ProfileType.Vibro.value -> ProfileType.Vibro
        ProfileType.Silent.value -> ProfileType.Silent
        ProfileType.Quiet.value -> ProfileType.Quiet
        ProfileType.Muted.value -> ProfileType.Muted
        ProfileType.Muted.value -> ProfileType.Muted
        else -> ProfileType.Aloud
    }