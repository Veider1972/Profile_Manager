package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.*
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun ProfileSetVolume(onDismiss: () -> Unit,
                     onAccept: (Int, Boolean, Int, Int, Int) -> Unit,
                     onCancel: () -> Unit) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileState.collectAsState()

    val commonVolume = rememberSaveable { mutableStateOf(state.commonVolume) }
    val isCommonVolume = rememberSaveable { mutableStateOf(state.useCommonVolume) }
    val notificationVolume = rememberSaveable { mutableStateOf(state.notificationVolume) }
    val mediaVolume = rememberSaveable { mutableStateOf(state.mediaVolume) }
    val alarmVolume = rememberSaveable { mutableStateOf(state.alarmVolume) }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = R.string.dialog_profile_set_volume_title)
        DialogPreferenceDesc(desc = R.string.dialog_profile_common_volume_title)
        VolumeSlider(value = commonVolume.value, onValueChange = { volume ->
            commonVolume.value = volume.toInt()
            if (isCommonVolume.value) {
                notificationVolume.value = commonVolume.value
                mediaVolume.value = commonVolume.value
                alarmVolume.value = commonVolume.value
            }
        })
        DialogCheckedPreference(desc = R.string.dialog_profile_common_volume, checked = isCommonVolume.value,
                                onClick = {
                                    isCommonVolume.value = !isCommonVolume.value
                                    if (isCommonVolume.value) {
                                        notificationVolume.value = commonVolume.value
                                        mediaVolume.value = commonVolume.value
                                        alarmVolume.value = commonVolume.value
                                    }
                                })
        DialogPreferenceDesc(desc = R.string.dialog_profile_notification_volume_title)
        VolumeSlider(value = notificationVolume.value, onValueChange = { volume -> notificationVolume.value = volume.toInt() })
        DialogPreferenceDesc(desc = R.string.dialog_profile_media_volume_title)
        VolumeSlider(value = mediaVolume.value, onValueChange = { volume -> mediaVolume.value = volume.toInt() })
        DialogPreferenceDesc(desc = R.string.dialog_profile_alarm_volume_title)
        VolumeSlider(value = alarmVolume.value, onValueChange = { volume -> alarmVolume.value = volume.toInt() })
        DialogAcceptCancelButtons(
            accept = { onAccept( commonVolume.value, isCommonVolume.value,
                                notificationVolume.value, mediaVolume.value, alarmVolume.value) },
            cancel = {})
    }
}

@Composable
fun VolumeSlider(value: Int, onValueChange: (Float) -> Unit) {
    CompositionLocalProvider(){
        Slider(
            value = value.toFloat(),
            valueRange = 0f..100f,
            onValueChange = { volume -> onValueChange(volume) },
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.single_padding))
        )
    }

}