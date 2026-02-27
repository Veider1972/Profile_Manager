package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.preference.WidgetNotificationType
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogSelector
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper


@Composable
fun NotificationTypeSetDialog(
    onDismiss: () -> Unit,
    onAccept: (WidgetNotificationType) -> Unit,
    onCancel: () -> Unit
) {

    val prefs: Preference = koinInject()
    val widget by prefs.widget.collectAsState()

    val notificationType = rememberSaveable {
        mutableStateOf(widget.notificationType)
    }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.widget_notification_type_title))
        DialogSelector(
            text = WidgetNotificationType.FULL.desc,
            checked = (notificationType.value == WidgetNotificationType.FULL),
            onCheckedChange = { notificationType.value = WidgetNotificationType.FULL })
        DialogSelector(
            text = WidgetNotificationType.SHORT.desc,
            checked = (notificationType.value == WidgetNotificationType.SHORT),
            onCheckedChange = { notificationType.value = WidgetNotificationType.SHORT })
        DialogSelector(
            text = WidgetNotificationType.NONE.desc,
            checked = (notificationType.value == WidgetNotificationType.NONE),
            onCheckedChange = { notificationType.value = WidgetNotificationType.NONE })
        DialogAcceptCancelButtons(accept = {
            onAccept(notificationType.value)
        }, cancel = { onCancel() })
    }
}



