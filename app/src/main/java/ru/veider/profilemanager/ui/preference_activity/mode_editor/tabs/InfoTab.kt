package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.enum.color
import ru.veider.profilemanager.viewmodel.PreferenceViewModel


@Composable
fun InfoTab(navController: NavController) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileSettingsState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_name_title),
                                  desc = "${stringResource(id = R.string.text_current)} ${state.name}",
                                  onClick = {
                                      //TODO
                                  })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_widget_mark_color_title),
                                  desc = stringResource(id = R.string.text_current),
                                  color = state.ringColor.color,
                                  onClick = {
                                      //TODO
                                  })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_widget_ring_color_title),
                                  desc = stringResource(id = R.string.text_current),
                                  color = state.symbolColor.color,
                                  onClick = {
                                      //TODO
                                  })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_widget_symbol_title),
                                  desc = stringResource(id = R.string.text_current),
                                  postImage = painterResource(id = state.symbol.imageId),
                                  onClick = {
                                      //TODO
                                  })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_use_phone_settings_title),
                                  desc = stringResource(id = R.string.profile_use_phone_settings_desc),
                                  onClick = {
                                      //TODO
                                  })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_copy_from_other_settings_title),
                                  desc = stringResource(id = R.string.profile_copy_from_other_settings_desc),
                                  onClick = {
                                      //TODO
                                  })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }
}