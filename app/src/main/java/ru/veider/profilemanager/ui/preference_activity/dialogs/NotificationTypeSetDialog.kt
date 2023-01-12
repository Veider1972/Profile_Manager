package ru.veider.profilemanager.ui.preference_activity.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.NotificationType
import ru.veider.profilemanager.ui.preference_activity.assets.desc
import ru.veider.profilemanager.ui.preference_activity.dialogs.elements.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.dialogs.elements.DialogSelector
import ru.veider.profilemanager.ui.preference_activity.dialogs.elements.DialogTitle
import ru.veider.profilemanager.viewmodel.PreferenceViewModel


@Composable
fun NotificationTypeSetDialog(onDismiss: () -> Unit,
                              onAccept: (NotificationType) -> Unit,
                              onCancel: () -> Unit) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.widgetState.collectAsState()

    val notificationType = remember {
        mutableStateOf(state.notificationType)
    }

    Dialog(onDismissRequest = onDismiss,
           properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)) {
        Card(shape = RoundedCornerShape(dimensionResource(id = R.dimen.single_padding)),
             modifier = Modifier
                 .padding(dimensionResource(id = R.dimen.single_padding)),
             elevation = dimensionResource(id = R.dimen.single_padding)
        ) {
            Column() {
                DialogTitle(text = stringResource(id = R.string.widget_notification_type_title))
                DialogSelector(text = NotificationType.FULL.desc,
                               checked = (notificationType.value == NotificationType.FULL),
                               onCheckedChange = { notificationType.value = NotificationType.FULL })
                DialogSelector(text = NotificationType.SHORT.desc,
                               checked = (notificationType.value == NotificationType.SHORT),
                               onCheckedChange = { notificationType.value = NotificationType.SHORT })
                DialogSelector(text = NotificationType.NONE.desc,
                               checked = (notificationType.value == NotificationType.NONE),
                               onCheckedChange = { notificationType.value = NotificationType.NONE })
                DialogAcceptCancelButtons(accept = {
                    onAccept(notificationType.value)
                }, cancel = { onCancel() })
            }
        }
    }
}

