package ru.veider.profilemanager.ui.preference_activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.*
import ru.veider.profilemanager.ui.preference_activity.dialogs.NotificationTypeSetDialog
import ru.veider.profilemanager.ui.preference_activity.dialogs.TransparencySetDialog
import ru.veider.profilemanager.ui.preference_activity.dialogs.elements.BackgroundColorSetDialog
import ru.veider.profilemanager.viewmodel.PreferenceViewModel


data class WidgetState(
    val notificationType: NotificationType = NotificationType.FULL,
    val backgroundColor: BackgroundColor = BackgroundColor.GREY,
    val backgroundTransparency: BackgroundTransparency = BackgroundTransparency(0)
)

@Composable
fun WidgetView() {

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
        SetUncheckedPreference(title = stringResource(id = R.string.widget_notification_type_title),
                               desc = "${stringResource(id = R.string.text_current)} ${state.notificationType.desc}",
                               action = {
                                   showNotificationTypeSelectDialog = true
                               })
        HorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        SetUncheckedPreference(title = stringResource(id = R.string.widget_background_color_title),
                               desc = "${stringResource(id = R.string.text_current)} ${state.backgroundColor.desc}",
                               action = { showBackgroundColorSetDialog = true })
        HorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        SetUncheckedPreference(title = stringResource(id = R.string.widget_transparency_title),
                               desc = "${stringResource(id = R.string.text_current)} ${state.backgroundTransparency.desc}",
                               action = {showTransparencySetDialog = true})
    }
}