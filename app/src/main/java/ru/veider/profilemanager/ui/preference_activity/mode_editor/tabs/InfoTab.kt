package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetColor
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetName
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetSymbol
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedPreference
import ru.veider.profilemanager.viewmodel.PreferenceViewModel


@Composable
fun InfoTab(navController: NavController) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileState.collectAsState()

    var showProfileSetNameDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileSetNameDialog)
        ProfileSetName(onDismiss = { showProfileSetNameDialog = false },
                                  onAccept = {
                                      viewModel.setProfileName(it)
                                      showProfileSetNameDialog = false
                                  },
                                  onCancel = { showProfileSetNameDialog = false })

    var showProfileSetMarkColorDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileSetMarkColorDialog)
        ProfileSetColor(onDismiss = { showProfileSetMarkColorDialog = false },
                        onAccept = {
                           viewModel.setProfileRingColor(it)
                           showProfileSetMarkColorDialog = false
                       },
                        onCancel = { showProfileSetMarkColorDialog = false })

    var showProfileSetRingColorDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileSetRingColorDialog)
        ProfileSetColor(onDismiss = { showProfileSetRingColorDialog = false },
                        onAccept = {
                            viewModel.setProfileRingColor(it)
                            showProfileSetRingColorDialog = false
                        },
                        onCancel = { showProfileSetRingColorDialog = false })

    var showProfileSetSymbolDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileSetSymbolDialog)
        ProfileSetSymbol(onDismiss = { showProfileSetSymbolDialog = false },
                        onAccept = {
                            viewModel.setProfileSymbol(it)
                            showProfileSetSymbolDialog = false
                        },
                        onCancel = { showProfileSetSymbolDialog = false })


    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        // Название профиля
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_name_title),
                                  desc = "${stringResource(id = R.string.text_current)} ${state.name}",
                                  onClick = {
                                      showProfileSetNameDialog = true
                                  })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Цвет значка виджета
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_widget_mark_color_title),
                                  desc = stringResource(id = R.string.text_current),
                                  color = state.ringColor,
                                  onClick = {
                                      showProfileSetMarkColorDialog = true
                                  })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Цвет кольца виджета
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_widget_ring_color_title),
                                  desc = stringResource(id = R.string.text_current),
                                  color = state.symbolColor,
                                  onClick = {
                                      showProfileSetRingColorDialog = true
                                  })
        // Значок виджета
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        DialogUncheckedPreference(title = stringResource(id = R.string.profile_widget_symbol_title),
                                  desc = stringResource(id = R.string.text_current),
                                  postImage = painterResource(id = state.symbol.imageId),
                                  onClick = {
                                      showProfileSetSymbolDialog = true
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