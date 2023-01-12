package ru.veider.profilemanager.ui.preference_activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.*
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

data class ToolsState(
    val notification: Boolean = false
)

@Composable
fun ToolsView() {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.toolsState.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        SetCaption(text = R.string.tools_notification_caption)
        SetCheckedPreference(title = R.string.tools_notification_title,
                             desc = R.string.tools_notification_desc,
                             checked = state.notification,
                             onCheckedChange = { viewModel.notificationChanged(!state.notification) })
        SetCaption(text = R.string.tools_reserving_caption)
        SetUncheckedPreference(title = R.string.tools_reserving_export_title, desc = R.string.tools_reserving_export_desc, action = {})
        HorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        SetUncheckedPreference(title = R.string.tools_reserving_import_title, desc = R.string.tools_reserving_import_desc, action = {})
        SetCaption(text = R.string.tools_default_caption)
        SetUncheckedPreference(title = R.string.tools_default_title, desc = R.string.tools_default_desc, action = {})
        HorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }

}

