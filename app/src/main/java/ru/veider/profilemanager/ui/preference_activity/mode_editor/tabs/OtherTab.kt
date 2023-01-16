package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogCheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogHorizontalThingDivider
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogUncheckedHorizontalPreference
import ru.veider.profilemanager.ui.preference_activity.assets.getScreenBrightness
import ru.veider.profilemanager.ui.preference_activity.assets.getScreenTimeout
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun OtherTab(navController: NavController) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileSettingsState.collectAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        // Управление тайм-аутом экрана
        DialogCheckedPreference(title = R.string.profile_screen_timeout_title,
                                desc = R.string.profile_screen_timeout_desc,
                                checked = state.isGuideScreenTimeout,
                                onClick = { viewModel.setWidgetGuideScreenTimeOut(!state.isGuideScreenTimeout) }
        )
        DialogUncheckedHorizontalPreference(enabled = state.isGuideScreenTimeout,
                                            preImage = painterResource(id = R.drawable.symbol_screen),
                                            title = stringResource(id = R.string.profile_screen_timeout),
                                            desc = if (state.isGuideScreenTimeout)
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
                                            onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление яркостью экрана
        DialogCheckedPreference(title = R.string.profile_screen_brightness_title,
                                desc = R.string.profile_screen_brightness_desc,
                                checked = state.isGuideScreenBrightness,
                                onClick = { viewModel.setWidgetGuideScreenBrightness(!state.isGuideScreenBrightness) }
        )
        DialogUncheckedHorizontalPreference(enabled = state.isGuideScreenBrightness,
                                            preImage = painterResource(id = R.drawable.symbol_brightness),
                                            title = stringResource(id = R.string.profile_screen_brightness),
                                            desc = if (state.isGuideScreenBrightness)
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
                                                    "${getScreenBrightness(context)} %"
                                                }",
                                            onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление автосинхронизацией
        DialogCheckedPreference(title = R.string.profile_sync_title,
                                desc = R.string.profile_sync_desc,
                                checked = state.isGuideSync,
                                onClick = { viewModel.setWidgetGuideSync(!state.isGuideSync) }
        )
        DialogCheckedPreference(enabled = state.isGuideSync,
                                image = painterResource(id = R.drawable.symbol_sync),
                                desc = R.string.profile_sync,
                                checked = state.isSync,
                                onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление поворотом экрана
        DialogCheckedPreference(title = R.string.profile_screen_rotation_title,
                                desc = R.string.profile_screen_rotation_desc,
                                checked = state.isGuideScreenRotation,
                                onClick = { viewModel.setWidgetGuideScreenRotation(!state.isGuideScreenRotation) }
        )
        DialogCheckedPreference(enabled = state.isGuideScreenRotation,
                                image = painterResource(id = R.drawable.symbol_screen_rotation),
                                desc = R.string.profile_screen_rotation,
                                checked = state.isScreenRotation,
                                onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление экраном блокировки
        DialogCheckedPreference(title = R.string.profile_screen_lock_title,
                                desc = R.string.profile_screen_lock_desc,
                                checked = state.isGuideScreenLock,
                                onClick = { viewModel.setWidgetGuideScreenLock(!state.isGuideScreenLock) }
        )
        DialogCheckedPreference(enabled = state.isGuideScreenLock,
                                image = painterResource(id = R.drawable.symbol_screen_lock),
                                desc = R.string.profile_screen_lock,
                                checked = state.isScreenLock,
                                onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }
}

