package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetColor
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetName
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetSymbol
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedPreference
import ru.veider.profilemanager.utils.updateBy


@Composable
fun InfoTab(navController: NavController) {

    val prefs: Preference = koinInject()
    val profiles by prefs.profiles.collectAsState()
    val currentProfile by prefs.currentProfile.collectAsState()
    val profile by rememberUpdatedState(profiles.firstOrNull { it.id == currentProfile })

    var showProfileSetNameDialog by rememberSaveable { mutableStateOf(false) }
    profile?.let { profile ->
        if (showProfileSetNameDialog)
            ProfileSetName(
                name = profile.name,
                onDismiss = { showProfileSetNameDialog = false },
                onAccept = { name ->
                    prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(name = name))
                    showProfileSetNameDialog = false
                },
                onCancel = { showProfileSetNameDialog = false })


        var showProfileSetSymbolColorDialog by rememberSaveable { mutableStateOf(false) }
        if (showProfileSetSymbolColorDialog)
            ProfileSetColor(
                color = profile.symbolColor,
                onDismiss = { showProfileSetSymbolColorDialog = false },
                onAccept = {
                    prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(symbolColor = it))
                    showProfileSetSymbolColorDialog = false
                },
                onCancel = { showProfileSetSymbolColorDialog = false })

        var showProfileSetRingColorDialog by rememberSaveable { mutableStateOf(false) }
        if (showProfileSetRingColorDialog)
            ProfileSetColor(
                color = profile.ringColor,
                onDismiss = { showProfileSetRingColorDialog = false },
                onAccept = {
                    prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(ringColor = it))
                    showProfileSetRingColorDialog = false
                },
                onCancel = { showProfileSetRingColorDialog = false })

        var showProfileSetSymbolDialog by rememberSaveable { mutableStateOf(false) }
        if (showProfileSetSymbolDialog)
            ProfileSetSymbol(
                symbol = profile.symbol,
                onDismiss = { showProfileSetSymbolDialog = false },
                onAccept = {
                    prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(symbol = it))
                    showProfileSetSymbolDialog = false
                },
                onCancel = { showProfileSetSymbolDialog = false })
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Название профиля
            DialogUncheckedPreference(
                title = stringResource(id = R.string.profile_name_title),
                desc = "${stringResource(id = R.string.text_current)} ${profile.name}",
                onClick = {
                    showProfileSetNameDialog = true
                })
            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))

            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
            // Цвет кольца виджета
            DialogUncheckedPreference(
                title = stringResource(id = R.string.profile_widget_ring_color_title),
                desc = stringResource(id = R.string.text_current),
                color = profile.ringColor,
                onClick = {
                    showProfileSetRingColorDialog = true
                })
            // Цвет значка виджета
            DialogUncheckedPreference(
                title = stringResource(id = R.string.profile_widget_mark_color_title),
                desc = stringResource(id = R.string.text_current),
                color = profile.symbolColor,
                onClick = {
                    showProfileSetSymbolColorDialog = true
                })
            // Значок виджета
            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
            DialogUncheckedPreference(
                title = stringResource(id = R.string.profile_widget_symbol_title),
                desc = stringResource(id = R.string.text_current),
                postImage = painterResource(id = profile.symbol.imageId(profile.symbolColor)),
                onClick = {
                    showProfileSetSymbolDialog = true
                })
            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
            DialogUncheckedPreference(
                title = stringResource(id = R.string.profile_use_phone_settings_title),
                desc = stringResource(id = R.string.profile_use_phone_settings_desc),
                onClick = {
                    //TODO
                })
            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
            DialogUncheckedPreference(
                title = stringResource(id = R.string.profile_copy_from_other_settings_title),
                desc = stringResource(id = R.string.profile_copy_from_other_settings_desc),
                onClick = {
                    //TODO
                })
            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        }
    }
}