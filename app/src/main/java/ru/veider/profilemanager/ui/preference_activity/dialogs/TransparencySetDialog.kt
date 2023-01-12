package ru.veider.profilemanager.ui.preference_activity.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.BackgroundTransparency
import ru.veider.profilemanager.ui.preference_activity.assets.desc
import ru.veider.profilemanager.ui.preference_activity.dialogs.elements.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.dialogs.elements.DialogTitle
import ru.veider.profilemanager.ui.theme.dialogText
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun TransparencySetDialog(onDismiss: () -> Unit,
                          onAccept: (BackgroundTransparency) -> Unit,
                          onCancel: () -> Unit) {
    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.widgetState.collectAsState()

    val transparency = rememberSaveable {
        mutableStateOf(state.backgroundTransparency.value)
    }
    Dialog(onDismissRequest = onDismiss,
           properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
    ) {
        Card(shape = RoundedCornerShape(dimensionResource(id = R.dimen.single_padding)),
             modifier = Modifier
                 .padding(dimensionResource(id = R.dimen.single_padding)),
             elevation = dimensionResource(id = R.dimen.single_padding)
        ) {
            Column() {
                DialogTitle(text = stringResource(id = R.string.widget_transparency_title))
//                DialogSelector(text = BackgroundColor.WHITE.desc,
//                               checked = (backgroundColor.value == BackgroundColor.WHITE),
//                               onCheckedChange = { backgroundColor.value = BackgroundColor.WHITE })
                Text(text = BackgroundTransparency(transparency.value).desc,
                     textAlign = TextAlign.Center,
                     style = dialogText,
                     modifier = Modifier.fillMaxWidth()
                )
                Slider(value = transparency.value.toFloat(),
                       valueRange = 0f..100f,
                       onValueChange = { transparency.value = it.toInt() },
                       modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.single_padding))
                )
                DialogAcceptCancelButtons(accept = {
                    onAccept(BackgroundTransparency(transparency.value))
                }, cancel = { onCancel() })
            }
        }
    }
}