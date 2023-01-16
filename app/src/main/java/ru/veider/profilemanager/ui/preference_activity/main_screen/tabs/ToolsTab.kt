package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogCheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceCaption
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedPreference
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun ToolsTab() {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.toolsState.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        DialogPreferenceCaption(text = R.string.tools_notification_caption)
        DialogCheckedPreference(title = R.string.tools_notification_title,
                                desc = R.string.tools_notification_desc,
                                checked = state.notification,
                                onClick = { viewModel.notificationChanged(!state.notification) })
        DialogPreferenceCaption(text = R.string.tools_reserving_caption)
        DialogUncheckedPreference(title = R.string.tools_reserving_export_title, desc = R.string.tools_reserving_export_desc, onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = R.string.tools_reserving_import_title, desc = R.string.tools_reserving_import_desc, onClick = {})
        DialogPreferenceCaption(text = R.string.tools_default_caption)
        DialogUncheckedPreference(title = R.string.tools_default_title, desc = R.string.tools_default_desc, onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }

}

