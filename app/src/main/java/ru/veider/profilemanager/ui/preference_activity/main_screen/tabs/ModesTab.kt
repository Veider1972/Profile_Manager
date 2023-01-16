package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.navigation.NavDestination
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.assets.PreferenceModeRow
import ru.veider.profilemanager.ui.theme.colorDay
import ru.veider.profilemanager.ui.theme.colorNight
import ru.veider.profilemanager.ui.theme.selectDialogModeFont

@Composable
fun ModesTab(navController: NavController) {
    Column(modifier = Modifier
        .padding(start = dimensionResource(id = R.dimen.double_padding),
                 bottom = dimensionResource(id = R.dimen.single_padding)
        )
        .fillMaxSize()
    ) {

        PreferenceModeRow(icon = R.drawable.widget_symbol_day, text = stringResource(id = R.string.mode_day_title), color = colorDay,
                          runMode = { /*TODO*/ }, timeMode = { /*TODO*/ }, editMode = {
                navController.navigate(NavDestination.ModeEditorScreen.destination)
            })
        PreferenceModeRow(icon = R.drawable.widget_symbol_night, text = stringResource(id = R.string.mode_night_title), color = colorNight,
                          runMode = { /*TODO*/ }, timeMode = { /*TODO*/ }, editMode = {
                navController.navigate(NavDestination.ModeEditorScreen.destination)
            })
    }
}

