package ru.veider.profilemanager.utils

import ru.veider.profilemanager.domain.Profile

fun List<Profile>.updateBy(profile: Profile) =
    this.map {
        if (it.type == profile.type)
            profile
        else
            it
    }