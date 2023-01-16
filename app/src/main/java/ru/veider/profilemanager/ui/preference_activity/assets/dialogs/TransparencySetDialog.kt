package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.preference_activity.assets.enum.WidgetBackgroundTransparency
import ru.veider.profilemanager.ui.preference_activity.assets.enum.desc
import ru.veider.profilemanager.ui.theme.dialogText
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun TransparencySetDialog(onDismiss: () -> Unit,
                          onAccept: (WidgetBackgroundTransparency) -> Unit,
                          onCancel: () -> Unit) {
    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.widgetState.collectAsState()

    val transparency = rememberSaveable {
        mutableStateOf(state.backgroundTransparency.value)
    }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.widget_transparency_title))
//                DialogSelector(text = BackgroundColor.WHITE.desc,
//                               checked = (backgroundColor.value == BackgroundColor.WHITE),
//                               onCheckedChange = { backgroundColor.value = BackgroundColor.WHITE })
        Text(text = WidgetBackgroundTransparency(transparency.value).desc,
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
            onAccept(WidgetBackgroundTransparency(transparency.value))
        }, cancel = { onCancel() })
    }
}