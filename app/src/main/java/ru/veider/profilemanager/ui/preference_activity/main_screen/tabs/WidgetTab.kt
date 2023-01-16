package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.NotificationTypeSetDialog
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.TransparencySetDialog
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.BackgroundColorSetDialog
import ru.veider.profilemanager.ui.preference_activity.assets.enum.desc
import ru.veider.profilemanager.viewmodel.PreferenceViewModel




@Composable
fun WidgetTab() {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.widgetState.collectAsState()

    var showNotificationTypeSelectDialog by rememberSaveable { mutableStateOf(false) }
    if (showNotificationTypeSelectDialog)
        NotificationTypeSetDialog(onDismiss = { showNotificationTypeSelectDialog = false },
                                  onAccept = {
                                      viewModel.setNotificationType(it)
                                      showNotificationTypeSelectDialog = false
                                  },
                                  onCancel = { showNotificationTypeSelectDialog = false })

    var showBackgroundColorSetDialog by rememberSaveable { mutableStateOf(false) }
    if (showBackgroundColorSetDialog)
        BackgroundColorSetDialog(onDismiss = { showBackgroundColorSetDialog = false },
                                 onAccept = {
                                     viewModel.setBackgroundColor(it)
                                     showBackgroundColorSetDialog = false
                                 },
                                 onCancel = { showBackgroundColorSetDialog = false })

    var showTransparencySetDialog by rememberSaveable { mutableStateOf(false) }
    if (showTransparencySetDialog)
        TransparencySetDialog(onDismiss = { showTransparencySetDialog = false },
                              onAccept = {
                                  viewModel.setBackgroundTransparency(it)
                                  showTransparencySetDialog = false
                              },
                              onCancel = { showTransparencySetDialog = false })

    Column(modifier = Modifier.fillMaxSize()) {
        DialogUncheckedPreference(title = stringResource(id = R.string.widget_notification_type_title),
                                  desc = "${stringResource(id = R.string.text_current)} ${state.notificationType.desc}",
                                  onClick = {
                                   showNotificationTypeSelectDialog = true
                               })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = stringResource(id = R.string.widget_background_color_title),
                                  desc = "${stringResource(id = R.string.text_current)} ${state.backgroundColor.desc}",
                                  onClick = { showBackgroundColorSetDialog = true })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = stringResource(id = R.string.widget_transparency_title),
                                  desc = "${stringResource(id = R.string.text_current)} ${state.backgroundTransparency.desc}",
                                  onClick = {showTransparencySetDialog = true})
    }
}