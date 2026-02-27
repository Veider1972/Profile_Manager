package ru.veider.profilemanager.domain.preference

import ru.veider.profilemanager.R

enum class VolumeMode {
    DONT_DISTURB, VIBRO, SILENCE, ALOUD;

    val descId
        get() =
            when (this) {
                DONT_DISTURB -> R.string.dialog_profile_dont_disturb
                VIBRO -> R.string.dialog_profile_vibro
                SILENCE -> R.string.dialog_profile_silence
                ALOUD -> R.string.dialog_profile_aloud
            }
    val resId
        get() =
            when (this) {
                DONT_DISTURB -> R.drawable.symbol_dont_disturb
                VIBRO -> R.drawable.symbol_vibration
                SILENCE -> R.drawable.symbol_silence
                ALOUD -> R.drawable.symbol_aloud
            }
}

val Int.asVolumeMode
    get() =
        when (this) {
            VolumeMode.DONT_DISTURB.ordinal -> VolumeMode.DONT_DISTURB
            VolumeMode.VIBRO.ordinal -> VolumeMode.VIBRO
            VolumeMode.SILENCE.ordinal -> VolumeMode.SILENCE
            VolumeMode.ALOUD.ordinal -> VolumeMode.ALOUD
            else -> VolumeMode.DONT_DISTURB
        }