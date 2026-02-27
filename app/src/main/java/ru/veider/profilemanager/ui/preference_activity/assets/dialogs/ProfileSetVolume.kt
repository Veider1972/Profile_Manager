package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.domain.preference.VolumeMode
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.*
import ru.veider.profilemanager.ui.theme.*

@Composable
fun ProfileSetVolume(
    profile: Profile,
    onDismiss: () -> Unit,
    onAccept: (VolumeMode, Boolean, Int, Int, Int, Int) -> Unit,
    onCancel: () -> Unit
) {

    var ringVolume by rememberSaveable { mutableStateOf(profile.ringVolume) }
    var useCommonVolume by rememberSaveable { mutableStateOf(profile.useCommonVolume) }
    var notificationVolume by rememberSaveable { mutableStateOf(profile.notificationVolume) }
    var mediaVolume by rememberSaveable { mutableStateOf(profile.mediaVolume) }
    var alarmVolume by rememberSaveable { mutableStateOf(profile.alarmVolume) }
    var checkedMode by rememberSaveable { mutableStateOf(profile.volumeMode) }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = R.string.dialog_profile_set_volume_title)
        Row(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.single_padding),
                end = dimensionResource(id = R.dimen.single_padding),
                bottom = 1.dp
            )
        ) {
            VolumeModeButton(
                icon = VolumeMode.DONT_DISTURB.resId,
                text = VolumeMode.DONT_DISTURB.descId,
                modifier = Modifier
                    .fillMaxWidth(1 / 2f)
                    .padding(end = 1.dp),
                checked = checkedMode == VolumeMode.DONT_DISTURB,
                onClick = {
                    checkedMode = VolumeMode.DONT_DISTURB
                }
            )
            VolumeModeButton(
                icon = VolumeMode.VIBRO.resId,
                text = VolumeMode.VIBRO.descId,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 1.dp),
                checked = checkedMode == VolumeMode.VIBRO,
                onClick = {
                    checkedMode = VolumeMode.VIBRO
                }
            )
        }
        Row(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.single_padding),
                end = dimensionResource(id = R.dimen.single_padding),
                bottom = dimensionResource(id = R.dimen.single_padding)
            )
        ) {
            VolumeModeButton(
                icon = VolumeMode.SILENCE.resId,
                text = VolumeMode.SILENCE.descId,
                modifier = Modifier
                    .fillMaxWidth(1 / 2f)
                    .padding(end = 1.dp),
                checked = checkedMode == VolumeMode.SILENCE,
                onClick = {
                    checkedMode = VolumeMode.SILENCE
                }
            )
            VolumeModeButton(
                icon = VolumeMode.ALOUD.resId,
                text = VolumeMode.ALOUD.descId,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 1.dp),
                checked = checkedMode == VolumeMode.ALOUD,
                onClick = {
                    checkedMode = VolumeMode.ALOUD
                }
            )
        }
        DialogPreferenceDesc(desc = R.string.dialog_profile_common_volume_title)
        VolumeSlider(value = ringVolume, onValueChange = { volume ->
            ringVolume = volume.toInt()
            if (useCommonVolume) {
                notificationVolume = ringVolume
                mediaVolume = ringVolume
                alarmVolume = ringVolume
            }
        }, enabled = (checkedMode == VolumeMode.ALOUD))
        DialogCheckedPreference(
            desc = R.string.dialog_profile_common_volume,
            checked = useCommonVolume,
            onClick = {
                useCommonVolume = !useCommonVolume
                if (useCommonVolume) {
                    notificationVolume = ringVolume
                    mediaVolume = ringVolume
                    alarmVolume = ringVolume
                }
            }, enabled = checkedMode == VolumeMode.ALOUD
        )
        DialogPreferenceDesc(desc = R.string.dialog_profile_notification_volume_title)
        VolumeSlider(
            value = notificationVolume,
            onValueChange = { volume -> notificationVolume = volume.toInt() },
            enabled = !useCommonVolume && checkedMode == VolumeMode.ALOUD
        )
        DialogPreferenceDesc(desc = R.string.dialog_profile_media_volume_title)
        VolumeSlider(
            value = mediaVolume,
            onValueChange = { volume -> mediaVolume = volume.toInt() },
            enabled = !useCommonVolume && checkedMode == VolumeMode.ALOUD
        )
        DialogPreferenceDesc(desc = R.string.dialog_profile_alarm_volume_title)
        VolumeSlider(
            value = alarmVolume,
            onValueChange = { volume -> alarmVolume = volume.toInt() },
            enabled = !useCommonVolume && checkedMode == VolumeMode.ALOUD
        )
        DialogAcceptCancelButtons(
            accept = {
                onAccept(
                    checkedMode, useCommonVolume, ringVolume,
                    notificationVolume, mediaVolume, alarmVolume
                )
            },
            cancel = { onCancel() })
    }
}

@Composable
fun VolumeModeButton(
    icon: Int,
    text: Int,
    modifier: Modifier,
    checked: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier,
//        elevation = if (checked) ButtonDefaults.elevation(
//            defaultElevation = 0.dp,
//            pressedElevation = 0.dp,
//            disabledElevation = 0.dp
//        ) else ButtonDefaults.elevation(
//            defaultElevation = dimensionResource(id = R.dimen.half_elevation),
//            pressedElevation = 0.dp,
//            disabledElevation = 0.dp
//        ),
        contentPadding = PaddingValues(
            start = 0.dp,
            end = 0.dp,
            top = dimensionResource(id = R.dimen.half_padding),
            bottom = dimensionResource(
                id = R.dimen.half_padding
            )
        ),
//        colors = if (checked) ButtonDefaults.buttonColors(
//            backgroundColor = colorPrimary,
//            contentColor = colorOnPrimary,
//            disabledBackgroundColor = colorSurface,
//            disabledContentColor = colorSurface
//        ) else ButtonDefaults.buttonColors(
//            backgroundColor = colorSurface,
//            contentColor = colorOnPrimary,
//            disabledBackgroundColor = colorSurface,
//            disabledContentColor = colorSurface
//        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                stringResource(id = text),
                textAlign = TextAlign.Center,
                maxLines = 2,
                style = dialogSmallText,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun VolumeSlider(value: Int, onValueChange: (Float) -> Unit, enabled: Boolean) {
    CompositionLocalProvider {
        Slider(
            value = value.toFloat(),
            enabled = enabled,
            valueRange = 0f..100f,
            onValueChange = { volume -> onValueChange(volume) },
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.single_padding))
        )
    }

}