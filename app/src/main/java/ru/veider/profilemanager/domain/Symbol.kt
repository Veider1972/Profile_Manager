package ru.veider.profilemanager.domain

import androidx.annotation.DrawableRes
import ru.veider.profilemanager.R

enum class Symbol(@DrawableRes val symbol: Int) {
    Day(R.drawable.profile_day),
    Night(R.drawable.profile_night),
    Vibro(R.drawable.profile_vibro),
    Silent(R.drawable.profile_silent),
    Quiet(R.drawable.profile_quiet),
    Muted(R.drawable.profile_muted),
    Aloud(R.drawable.profile_aloud)
}