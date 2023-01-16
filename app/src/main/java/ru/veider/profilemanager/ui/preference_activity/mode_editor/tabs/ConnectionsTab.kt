package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogCheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun ConnectionsTab(navController: NavController) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileSettingsState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Управление Wi-Fi
        DialogCheckedPreference(title = R.string.profile_wifi_title,
                                desc = R.string.profile_wifi_desc,
                                checked = state.isGuideWiFi,
                                onClick = { viewModel.setWidgetGuideWiFi(!state.isGuideWiFi) }
        )
        DialogCheckedPreference(enabled = state.isGuideWiFi,
                                image = painterResource(id = R.drawable.symbol_wifi),
                                desc = R.string.profile_wifi,
                                checked = state.isWiFi,
                                onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление мобильными данными
        DialogCheckedPreference(title = R.string.profile_mobile_data_title,
                                desc = R.string.profile_mobile_data_desc,
                                checked = state.isGuideMobileData,
                                onClick = { viewModel.setWidgetGuideMobileData(!state.isGuideMobileData) }
        )
        DialogCheckedPreference(enabled = state.isGuideMobileData,
                                image = painterResource(id = R.drawable.symbol_mobile_data),
                                desc = R.string.profile_mobile_data,
                                checked = state.isMobileData,
                                onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление Bluetooth
        DialogCheckedPreference(title = R.string.profile_bluetooth_title,
                                desc = R.string.profile_bluetooth_desc,
                                checked = state.isGuideBluetooth,
                                onClick = { viewModel.setWidgetGuideBluetooth(!state.isGuideBluetooth) }
        )
        DialogCheckedPreference(enabled = state.isGuideBluetooth,
                                image = painterResource(id = R.drawable.symbol_bluetooth),
                                desc = R.string.profile_bluetooth,
                                checked = state.isBluetooth,
                                onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление точкой доступа Wi-Fi
        DialogCheckedPreference(title = R.string.profile_access_point_title,
                                desc = R.string.profile_access_point_desc,
                                checked = state.isGuideAccessPoint,
                                onClick = { viewModel.setWidgetGuideAccessPoint(!state.isGuideAccessPoint) }
        )
        DialogCheckedPreference(enabled = state.isGuideAccessPoint,
                                image = painterResource(id = R.drawable.symbol_acces_point),
                                desc = R.string.profile_access_point,
                                checked = state.isAccessPoint,
                                onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }

}
