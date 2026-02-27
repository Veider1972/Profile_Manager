package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import android.media.RingtoneManager
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.navigation.NavController
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.preference.VolumeMode
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.*
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.*
import ru.veider.profilemanager.ui.preference_activity.state.PhoneCapabilities
import ru.veider.profilemanager.utils.updateBy

@Composable
fun SoundsTab(navController: NavController) {

    val prefs: Preference = koinInject()
    val profiles by prefs.profiles.collectAsState()
    val currentProfile by prefs.currentProfile.collectAsState()
    val profile by rememberUpdatedState(profiles.firstOrNull { it.id == currentProfile })
    val capability: PhoneCapabilities = koinInject()
    val context = LocalContext.current

    profile?.let { profile ->
        var showProfileSetVolumeDialog by rememberSaveable { mutableStateOf(false) }
        if (showProfileSetVolumeDialog)
            ProfileSetVolume(
                profile = profile,
                onDismiss = { showProfileSetVolumeDialog = false },
                onAccept = { volumeMode, useCommonVolume,ringVolume,  notificationVolume, mediaVolume, alarmVolume ->
                    prefs.profiles.value = prefs.profiles.value.updateBy(
                        profile.copy(
                            volumeMode = volumeMode,
                            useCommonVolume = useCommonVolume,
                            ringVolume = ringVolume,
                            notificationVolume = notificationVolume,
                            mediaVolume = mediaVolume,
                            alarmVolume = alarmVolume
                        )
                    )
                    showProfileSetVolumeDialog = false
                },
                onCancel = { showProfileSetVolumeDialog = false })

        Column(modifier = Modifier.fillMaxSize()) {
            // Управление громкостью вызовов и вибрацией
            DialogCheckedPreference(title = R.string.profile_volume_title,
                desc = R.string.profile_volume_desc,
                checked = profile.guideVolume,
                onClick = {
                    prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(guideVolume = !profile.guideVolume))
                }
            )
            DialogUncheckedHorizontalPreference(
                enabled = profile.guideVolume,
                preImage = painterResource(
                    id = if (!profile.guideVolume)
                        R.drawable.symbol_aloud
                    else if (profile.volumeMode == VolumeMode.DONT_DISTURB)
                        R.drawable.symbol_dont_disturb
                    else if (profile.volumeMode == VolumeMode.VIBRO)
                        R.drawable.symbol_vibration
                    else if (profile.volumeMode == VolumeMode.SILENCE)
                        R.drawable.symbol_vibration
                    else R.drawable.symbol_aloud
                ),
                title = stringResource(
                    id = if (!profile.guideVolume)
                        R.string.profile_volume
                    else if (profile.volumeMode == VolumeMode.DONT_DISTURB)
                        VolumeMode.DONT_DISTURB.descId
                    else if (profile.volumeMode == VolumeMode.VIBRO)
                        VolumeMode.VIBRO.descId
                    else if (profile.volumeMode == VolumeMode.SILENCE)
                        VolumeMode.SILENCE.descId
                    else R.string.profile_volume
                ),
                desc = if (profile.volumeMode == VolumeMode.ALOUD) {
                    "${stringResource(id = R.string.text_current)} ${
                        if (profile.useCommonVolume)
                            "${
                                profile.ringVolume
                            }% ${
                                stringResource(id = R.string.profile_volume_common_desc)
                            }" else
                            "${
                                profile.ringVolume
                            }%, ${
                                profile.notificationVolume
                            }%, ${
                                profile.mediaVolume
                            }%, ${
                                profile.alarmVolume
                            }%"
                    }"
                } else "",
                onClick = { showProfileSetVolumeDialog = true })
            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        }
    }

}


