package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogCheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceCaption
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedPreference

@Composable
fun ToolsTab() {

    val prefs: Preference = koinInject()
    val state by prefs.widget.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        DialogPreferenceCaption(text = R.string.tools_notification_caption)
        DialogCheckedPreference(title = R.string.tools_notification_title,
                                desc = R.string.tools_notification_desc,
                                checked = state.notificationOn,
                                onClick = { prefs.widget.value = prefs.widget.value.copy(notificationOn = !state.notificationOn)  }
        )
        DialogPreferenceCaption(text = R.string.tools_reserving_caption)
        DialogUncheckedPreference(title = R.string.tools_reserving_export_title, desc = R.string.tools_reserving_export_desc, onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = R.string.tools_reserving_import_title, desc = R.string.tools_reserving_import_desc, onClick = {})
        DialogPreferenceCaption(text = R.string.tools_default_caption)
        DialogUncheckedPreference(title = R.string.tools_default_title, desc = R.string.tools_default_desc, onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }

}

