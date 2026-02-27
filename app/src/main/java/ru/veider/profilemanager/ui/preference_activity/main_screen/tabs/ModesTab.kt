package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.navigation.NavDestination
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.assets.PreferenceModeRow
import ru.veider.profilemanager.utils.updateBy

@Composable
fun ModesTab(navController: NavController) {

    val prefs: Preference = koinInject()
    val profiles by prefs.profiles.collectAsState()
    val widget by prefs.widget.collectAsState()


    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.double_padding),
                bottom = dimensionResource(id = R.dimen.single_padding)
            )
            .fillMaxSize()
    ) {
        profiles.forEach {
            PreferenceModeRow(
                widgetBackground = widget.backgroundColor,
                ringColor = it.ringColor,
                symbol = it.symbol,
                symbolColor = it.symbolColor,
                text = it.name,
                textColor = it.symbolColor.color,
                runMode = { prefs.currentProfile.value = it.id },
                timeMode = { /*TODO*/ },
                editMode = {
                    Log.d("TAG", it.id.toString())
                    prefs.currentProfile.value = it.id
                    navController.navigate(NavDestination.ModeEditorScreen.destination)
                })
        }
    }
}

