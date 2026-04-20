package ru.veider.profilemanager.repo

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.domain.ProfileType
import ru.veider.profilemanager.domain.Widget

interface Preference {
    val widget: MutableStateFlow<Widget>
    val profiles: MutableStateFlow<List<Profile>>
    val selectedProfilesType: MutableStateFlow<List<ProfileType>>
    val currentProfileType: MutableStateFlow<ProfileType>
    fun getColor(@ColorRes res: Int): Color
    fun getString(@StringRes res: Int): String
}