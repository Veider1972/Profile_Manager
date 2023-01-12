package ru.veider.profilemanager.ui.preference_activity.dialogs.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.BackgroundColor
import ru.veider.profilemanager.ui.preference_activity.assets.desc
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun BackgroundColorSetDialog(onDismiss: () -> Unit,
                             onAccept: (BackgroundColor) -> Unit,
                             onCancel: () -> Unit) {
    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.widgetState.collectAsState()

    val backgroundColor = rememberSaveable {
        mutableStateOf(state.backgroundColor)
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
                DialogTitle(text = stringResource(id = R.string.widget_background_color_title))
                DialogSelector(text = BackgroundColor.WHITE.desc,
                               checked = (backgroundColor.value == BackgroundColor.WHITE),
                               onCheckedChange = { backgroundColor.value = BackgroundColor.WHITE })
                DialogSelector(text = BackgroundColor.GREY.desc,
                               checked = (backgroundColor.value == BackgroundColor.GREY),
                               onCheckedChange = { backgroundColor.value = BackgroundColor.GREY })
                DialogSelector(text = BackgroundColor.BLACK.desc,
                               checked = (backgroundColor.value == BackgroundColor.BLACK),
                               onCheckedChange = { backgroundColor.value = BackgroundColor.BLACK })
                DialogSelector(text = BackgroundColor.NONE.desc,
                               checked = (backgroundColor.value == BackgroundColor.NONE),
                               onCheckedChange = { backgroundColor.value = BackgroundColor.NONE })
                DialogAcceptCancelButtons(accept = {
                    onAccept(backgroundColor.value)
                }, cancel = { onCancel() })
            }
        }
    }
}