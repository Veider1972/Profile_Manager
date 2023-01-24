package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetScreenBrightness
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetScreenTimeout
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogCheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedHorizontalPreference
import ru.veider.profilemanager.ui.preference_activity.assets.getPairTimeout
import ru.veider.profilemanager.ui.preference_activity.assets.getScreenBrightness
import ru.veider.profilemanager.ui.preference_activity.assets.getScreenBrightnessAuto
import ru.veider.profilemanager.ui.preference_activity.assets.getScreenTimeout
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun OtherTab(navController: NavController) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileState.collectAsState()
    val context = LocalContext.current

    var showProfileSetScreenTimeoutDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileSetScreenTimeoutDialog)
        ProfileSetScreenTimeout(currentTime = getPairTimeout(state.screenTimeout),
                                onDismiss = { showProfileSetScreenTimeoutDialog = false },
                                onAccept = {
                                    viewModel.setProfileScreenTimeOut(it)
                                    showProfileSetScreenTimeoutDialog = false
                                },
                                onCancel = { showProfileSetScreenTimeoutDialog = false })

    var showProfileSetScreenBrightnessDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileSetScreenBrightnessDialog)
        ProfileSetScreenBrightness(brightness = state.screenBrightness,
                                   onDismiss = { showProfileSetScreenBrightnessDialog = false },
                                   onAccept = {
                                       viewModel.setProfileScreenBrightness(it)
                                       showProfileSetScreenBrightnessDialog = false
                                   },
                                   onCancel = { showProfileSetScreenBrightnessDialog = false })

    Column(modifier = Modifier.fillMaxSize()) {
        // Управление тайм-аутом экрана
        DialogCheckedPreference(title = R.string.profile_screen_timeout_title,
                                desc = R.string.profile_screen_timeout_desc,
                                checked = state.guideScreenTimeout,
                                onClick = { viewModel.setProfileGuideScreenTimeOut(!state.guideScreenTimeout) }
        )
        DialogUncheckedHorizontalPreference(enabled = state.guideScreenTimeout,
                                            preImage = painterResource(id = R.drawable.symbol_screen),
                                            title = stringResource(id = R.string.profile_screen_timeout),
                                            desc = if (state.guideScreenTimeout)
                                                "${
                                                    stringResource(id = R.string.text_current)
                                                } ${
                                                    getScreenTimeout(context, state.screenTimeout)
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
        DialogCheckedPreference(title = R.string.profile_screen_brightness_title,
                                desc = R.string.profile_screen_brightness_desc,
                                checked = state.guideScreenBrightness,
                                onClick = { viewModel.setProfileGuideScreenBrightness(!state.guideScreenBrightness) }
        )
        DialogUncheckedHorizontalPreference(enabled = state.guideScreenBrightness,
                                            preImage = painterResource(id = R.drawable.symbol_brightness),
                                            title = stringResource(id = R.string.profile_screen_brightness),
                                            desc = if (state.guideScreenBrightness)
                                                "${
                                                    stringResource(id = R.string.text_current)
                                                } ${
                                                    if (state.screenBrightness < 0)
                                                        stringResource(id = R.string.profile_screen_brightness_auto)
                                                    else
                                                        "${state.screenBrightness} %"
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
        // Управление автосинхронизацией
        DialogCheckedPreference(title = R.string.profile_sync_title,
                                desc = R.string.profile_sync_desc,
                                checked = state.guideSync,
                                onClick = { viewModel.setProfileGuideSync(!state.guideSync) }
        )
        DialogCheckedPreference(enabled = state.guideSync,
                                image = painterResource(id = R.drawable.symbol_sync),
                                desc = R.string.profile_sync,
                                checked = state.syncOn,
                                onClick = { viewModel.setProfileSync(!state.syncOn) })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление поворотом экрана
        DialogCheckedPreference(title = R.string.profile_screen_rotation_title,
                                desc = R.string.profile_screen_rotation_desc,
                                checked = state.guideScreenRotation,
                                onClick = { viewModel.setProfileGuideScreenRotation(!state.guideScreenRotation) }
        )
        DialogCheckedPreference(enabled = state.guideScreenRotation,
                                image = painterResource(id = R.drawable.symbol_screen_rotation),
                                desc = R.string.profile_screen_rotation,
                                checked = state.screenRotationOn,
                                onClick = { viewModel.setProfileScreenRotation(!state.screenRotationOn) })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление экраном блокировки
        DialogCheckedPreference(title = R.string.profile_screen_lock_title,
                                desc = R.string.profile_screen_lock_desc,
                                checked = state.guideScreenLock,
                                onClick = { viewModel.setProfileGuideScreenLock(!state.guideScreenLock) }
        )
        DialogCheckedPreference(enabled = state.guideScreenLock,
                                image = painterResource(id = R.drawable.symbol_screen_lock),
                                desc = R.string.profile_screen_lock,
                                checked = state.screenLockOn,
                                onClick = { viewModel.setProfileScreenLock(!state.screenLockOn) })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }
}

