package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.navigation.NavDestination
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.assets.PreferenceModeRow
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun ModesTab(navController: NavController) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val profiles by viewModel.profilesState.collectAsState()
    val widget by viewModel.widgetState.collectAsState()


    Column(modifier = Modifier
        .padding(start = dimensionResource(id = R.dimen.double_padding),
                 bottom = dimensionResource(id = R.dimen.single_padding)
        )
        .fillMaxSize()
    ) {
        profiles.data.forEach {
            PreferenceModeRow(widgetBackground = widget.backgroundColor,
                              ringColor = it.ringColor,
                              symbol = it.symbol,
                              symbolColor = it.symbolColor,
                              text = it.name,
                              textColor = it.symbolColor.color,
                              runMode = { viewModel.setCurrentProfile(it) },
                              timeMode = { /*TODO*/ },
                              editMode = {
                                  Log.d("TAG", it.id.toString())
                                  viewModel.setProfileState(it.id)
                                  navController.navigate(NavDestination.ModeEditorScreen.destination)
                              })
        }
    }
}

