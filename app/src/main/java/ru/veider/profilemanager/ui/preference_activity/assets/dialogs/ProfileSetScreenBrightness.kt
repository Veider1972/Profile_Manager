package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogCheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.theme.dialogText

@Composable
fun ProfileSetScreenBrightness(brightness: Int,
                               onDismiss: () -> Unit,
                               onAccept: (Int) -> Unit,
                               onCancel: () -> Unit) {

    val auto = rememberSaveable { mutableStateOf(brightness < 0) }
    val intensity = rememberSaveable { mutableStateOf(brightness) }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.profile_screen_brightness_title))
        DialogCheckedPreference(desc = stringResource(id = R.string.dialog_profile_set_screen_brightness_auto),
                                checked = auto.value,
                                onClick = { auto.value = !auto.value })

        Slider(value = if (auto.value) 0f else intensity.value.toFloat(),
               enabled = !auto.value,
               valueRange = 0f..100f,
               onValueChange = { intensity.value = it.toInt() },
               modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.single_padding))
        )
        Text(if (!auto.value) "${intensity.value}%" else stringResource(id = R.string.profile_screen_brightness_auto),
             modifier = Modifier.fillMaxWidth(),
             textAlign = TextAlign.Center,
             style = dialogText
        )
        DialogAcceptCancelButtons(accept = {
            onAccept(if (auto.value) -1 else intensity.value)
        }, cancel = { onCancel() })
    }
}