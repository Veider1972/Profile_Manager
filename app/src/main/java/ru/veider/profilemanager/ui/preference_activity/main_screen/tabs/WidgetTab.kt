package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.NotificationTypeSetDialog
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.TransparencySetDialog
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.BackgroundColorSetDialog
import ru.veider.profilemanager.utils.updateWidget


@Composable
fun WidgetTab() {

    val context = LocalContext.current
    val prefs: Preference = koinInject()
    val widget by prefs.widget.collectAsState()

    var showNotificationTypeSelectDialog by rememberSaveable { mutableStateOf(false) }
    if (showNotificationTypeSelectDialog)
        NotificationTypeSetDialog(
            onDismiss = { showNotificationTypeSelectDialog = false },
            onAccept = {
                prefs.widget.value = prefs.widget.value.copy(notificationType = it)
                showNotificationTypeSelectDialog = false
            },
            onCancel = { showNotificationTypeSelectDialog = false })

    var showBackgroundColorSetDialog by rememberSaveable { mutableStateOf(false) }
    if (showBackgroundColorSetDialog)
        BackgroundColorSetDialog(
            onDismiss = { showBackgroundColorSetDialog = false },
            onAccept = {
                prefs.widget.value = prefs.widget.value.copy(backgroundColor = it)
                context.updateWidget()
                showBackgroundColorSetDialog = false
            },
            onCancel = { showBackgroundColorSetDialog = false })

    var showTransparencySetDialog by rememberSaveable { mutableStateOf(false) }
    if (showTransparencySetDialog)
        TransparencySetDialog(
            widget = widget,
            onDismiss = { showTransparencySetDialog = false },
            onAccept = {
                prefs.widget.value = prefs.widget.value.copy(backgroundTransparency = it)
                context.updateWidget()
                showTransparencySetDialog = false
            },
            onCancel = { showTransparencySetDialog = false })

    Column(modifier = Modifier.fillMaxSize()) {
        DialogUncheckedPreference(
            title = stringResource(id = R.string.widget_notification_type_title),
            desc = "${stringResource(id = R.string.text_current)} ${widget.notificationType.desc}",
            onClick = {
                showNotificationTypeSelectDialog = true
            })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(
            title = stringResource(id = R.string.widget_background_color_title),
            desc = "${stringResource(id = R.string.text_current)} ${widget.backgroundColor.desc}",
            onClick = { showBackgroundColorSetDialog = true })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(
            title = stringResource(id = R.string.widget_transparency_title),
            desc = "${stringResource(id = R.string.text_current)} ${widget.backgroundTransparency.desc}",
            onClick = { showTransparencySetDialog = true })
    }
}