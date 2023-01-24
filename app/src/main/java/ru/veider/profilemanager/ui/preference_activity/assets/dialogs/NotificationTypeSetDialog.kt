package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogSelector
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetNotificationType
import ru.veider.profilemanager.viewmodel.PreferenceViewModel


@Composable
fun NotificationTypeSetDialog(onDismiss: () -> Unit,
                              onAccept: (WidgetNotificationType) -> Unit,
                              onCancel: () -> Unit) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.widgetState.collectAsState()

    val notificationType = remember {
        mutableStateOf(state.notificationType)
    }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.widget_notification_type_title))
        DialogSelector(text = WidgetNotificationType.FULL.desc,
                       checked = (notificationType.value == WidgetNotificationType.FULL),
                       onCheckedChange = { notificationType.value = WidgetNotificationType.FULL })
        DialogSelector(text = WidgetNotificationType.SHORT.desc,
                       checked = (notificationType.value == WidgetNotificationType.SHORT),
                       onCheckedChange = { notificationType.value = WidgetNotificationType.SHORT })
        DialogSelector(text = WidgetNotificationType.NONE.desc,
                       checked = (notificationType.value == WidgetNotificationType.NONE),
                       onCheckedChange = { notificationType.value = WidgetNotificationType.NONE })
        DialogAcceptCancelButtons(accept = {
            onAccept(notificationType.value)
        }, cancel = { onCancel() })
    }
}



