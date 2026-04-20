package ru.veider.profilemanager.ui.profileManager.mainScreen.screens.assets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.koin.androidContext
import org.koin.compose.*
import ru.veider.profilemanager.R
import ru.veider.profilemanager.data.dayProfile
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.*
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.assets.buttons.Chip
import ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets.DrawWidget
import ru.veider.profilemanager.ui.theme.selectDialogModeFont

@Composable
fun PreferenceModeRow(
    modifier: Modifier = Modifier,
    widgetBackground: WidgetBackground,
    profile: Profile,
    runMode: () -> Unit,
    editMode: () -> Unit
) {

    val prefs: Preference = koinInject()
    val currentProfileType by prefs.currentProfileType.collectAsState()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.single_padding)),
    ) {
        DrawWidget(
            size = LocalMinimumInteractiveComponentSize.current,
            widgetBackground = widgetBackground,
            color = profile.color,
            symbol = profile.symbol)
        Text(
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.single_padding))
                .weight(1f),
            text = profile.name,
            overflow = TextOverflow.Ellipsis,
            style = selectDialogModeFont
        )
        Chip(imageId = R.drawable.run_mode, selected = profile.type == currentProfileType, onClick = runMode)
        Chip(imageId = R.drawable.edit_mode, selected = false, onClick = editMode)
    }
}



@Preview(apiLevel = 35)
@Composable
private fun Preview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    KoinApplication(application = {
        androidContext(context)
        modules(appModule, gsonModule)
    }) {
        PreferenceModeRow(
            widgetBackground = WidgetBackground.White,
            profile = dayProfile(context),
            runMode = {},
            editMode = {}
        )
    }

}
