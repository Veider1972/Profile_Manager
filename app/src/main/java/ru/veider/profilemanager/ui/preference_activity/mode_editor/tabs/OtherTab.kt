package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.navigation.NavController
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.preference_activity.assets.*
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.*
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.*
import ru.veider.profilemanager.utils.updateBy

@Composable
fun OtherTab(navController: NavController) {

    val prefs: Preference = koinInject()
    val profiles by prefs.profiles.collectAsState()
    val currentProfile by prefs.currentProfile.collectAsState()
    val profile by rememberUpdatedState(profiles.firstOrNull { it.id == currentProfile })
    val context = LocalContext.current

    profile?.let { profile ->
        var showProfileSetScreenTimeoutDialog by rememberSaveable { mutableStateOf(false) }
        if (showProfileSetScreenTimeoutDialog)
            ProfileSetScreenTimeout(
                currentTime = getPairTimeout(profile.screenTimeout),
                onDismiss = { showProfileSetScreenTimeoutDialog = false },
                onAccept = {
                    prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(screenTimeout = it))
                    showProfileSetScreenTimeoutDialog = false
                },
                onCancel = { showProfileSetScreenTimeoutDialog = false })

        var showProfileSetScreenBrightnessDialog by rememberSaveable { mutableStateOf(false) }
        if (showProfileSetScreenBrightnessDialog)
            ProfileSetScreenBrightness(
                brightness = profile.screenBrightness,
                onDismiss = { showProfileSetScreenBrightnessDialog = false },
                onAccept = {
                    prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(screenBrightness = it))
                    showProfileSetScreenBrightnessDialog = false
                },
                onCancel = { showProfileSetScreenBrightnessDialog = false })

        Column(modifier = Modifier.fillMaxSize()) {
            // Управление тайм-аутом экрана
            DialogCheckedPreference(
                title = R.string.profile_screen_timeout_title,
                desc = R.string.profile_screen_timeout_desc,
                checked = profile.guideScreenTimeout,
                onClick = { prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(guideScreenTimeout = !profile.guideScreenTimeout)) }
            )
            DialogUncheckedHorizontalPreference(
                enabled = profile.guideScreenTimeout,
                preImage = painterResource(id = R.drawable.symbol_screen),
                title = stringResource(id = R.string.profile_screen_timeout),
                desc = if (profile.guideScreenTimeout)
                    "${
                        stringResource(id = R.string.text_current)
                    } ${
                        getScreenTimeout(context, profile.screenTimeout)
                    }" else
                    "${
                        stringResource(id = R.string.text_current)
                    } ${
                        getScreenTimeout(context)
                    }",
                onClick = {
                    showProfileSetScreenTimeoutDialog = true
                })
            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
            // Управление яркостью экрана
            DialogCheckedPreference(
                title = R.string.profile_screen_brightness_title,
                desc = R.string.profile_screen_brightness_desc,
                checked = profile.guideScreenBrightness,
                onClick = { prefs.profiles.value = prefs.profiles.value.updateBy(profile.copy(guideScreenBrightness = !profile.guideScreenBrightness)) }
            )
            DialogUncheckedHorizontalPreference(
                enabled = profile.guideScreenBrightness,
                preImage = painterResource(id = R.drawable.symbol_brightness),
                title = stringResource(id = R.string.profile_screen_brightness),
                desc = if (profile.guideScreenBrightness)
                    "${
                        stringResource(id = R.string.text_current)
                    } ${
                        if (profile.screenBrightness < 0)
                            stringResource(id = R.string.profile_screen_brightness_auto)
                        else
                            "${profile.screenBrightness} %"
                    }" else
                    "${
                        stringResource(id = R.string.text_current)
                    } ${
                        if (getScreenBrightnessAuto(context))
                            stringResource(id = R.string.profile_screen_brightness_auto)
                        else
                            "${getScreenBrightness(context)} %"
                    }",
                onClick = { showProfileSetScreenBrightnessDialog = true })
            DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        }
    }

}

