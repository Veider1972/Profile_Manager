package ru.veider.profilemanager.utils

import android.provider.ContactsContract
import ru.veider.profilemanager.domain.Profile

fun List<Profile>.updateBy(profile: Profile) =
    this.map {
        if (it.id == profile.id)
            profile
        else
            it
    }