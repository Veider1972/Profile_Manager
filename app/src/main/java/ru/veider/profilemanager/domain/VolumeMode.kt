package ru.veider.profilemanager.domain

import ru.veider.profilemanager.R

enum class VolumeMode {
    VIBRO, SILENCE, ALOUD;

    val descId
        get() =
            when (this) {
                VIBRO -> R.string.vibration
                SILENCE -> R.string.dialog_profile_silence
                ALOUD -> R.string.sound
            }
    val resId
        get() =
            when (this) {
                VIBRO -> R.drawable.symbol_vibration
                SILENCE -> R.drawable.symbol_silence
                ALOUD -> R.drawable.symbol_aloud
            }
}

val Int.asVolumeMode
    get() =
        when (this) {
            VolumeMode.VIBRO.ordinal -> VolumeMode.VIBRO
            VolumeMode.SILENCE.ordinal -> VolumeMode.SILENCE
            else -> VolumeMode.ALOUD
        }