package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import android.media.RingtoneManager
import android.net.Uri
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
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.*
import ru.veider.profilemanager.ui.preference_activity.assets.getRingtoneTitle
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun SoundsTab(navController: NavController) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileSettingsState.collectAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        // Управление вибрацией
        DialogCheckedPreference(title = R.string.profile_vibration_title,
                                desc = R.string.profile_vibration_desc,
                                checked = state.isGuideVibration,
                                onClick = { viewModel.setWidgetGuideVibration(!state.isGuideVibration) }
        )
        DialogCheckedPreference(enabled = state.isGuideVibration,
                                image = painterResource(id = R.drawable.symbol_vibration),
                                desc = R.string.profile_vibration,
                                checked = state.isVibration,
                                onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление громкостью вызовов
        DialogCheckedPreference(title = R.string.profile_volume_title,
                                desc = R.string.profile_volume_desc,
                                checked = state.isGuideVolume,
                                onClick = { viewModel.setWidgetGuideVolume(!state.isGuideVolume) }
        )
        DialogUncheckedHorizontalPreference(enabled = state.isGuideVolume,
                                            preImage = painterResource(id = R.drawable.symbol_aloud),
                                            title = stringResource(id = R.string.profile_volume),
                                            desc = if (state.isCommonVolume)
                                                "${
                                                    stringResource(id = R.string.text_current)
                                                } ${
                                                    state.commonVolume
                                                } ${
                                                    stringResource(id = R.string.profile_volume_common_desc)
                                                }" else
                                                "${
                                                    stringResource(id = R.string.text_current)
                                                } ${
                                                    state.notificationVolume
                                                }, ${
                                                    state.mediaVolume
                                                }, ${
                                                    state.alarmVolume
                                                }",
                                            onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление мелодией вызова
        DialogCheckedPreference(title = R.string.profile_call_melody_title,
                                desc = R.string.profile_call_melody_desc,
                                checked = state.isGuideCallMelody,
                                onClick = { viewModel.setWidgetGuideCallMelody(!state.isGuideCallMelody) }
        )
        DialogUncheckedVerticalPreference(enabled = state.isGuideCallMelody,
                                          preImage = painterResource(id = R.drawable.symbol_calls),
                                          title = stringResource(id = R.string.profile_call_melody),
                                          desc = if (state.isGuideCallMelody)
                                              "${
                                                  stringResource(id = R.string.text_current)
                                              } ${
                                                  if (state.callMelody == Uri.EMPTY)
                                                      stringResource(id = R.string.text_not_set)
                                                  else
                                                      getRingtoneTitle(context, state.callMelody)
                                              }" else
                                              "${
                                                  stringResource(id = R.string.text_current)
                                              } ${
                                                  getRingtoneTitle(context,
                                                                   RingtoneManager.getActualDefaultRingtoneUri(context,
                                                                                                               RingtoneManager.TYPE_RINGTONE
                                                                   )
                                                  )
                                              }",
                                          onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление звуком уведомлений
        DialogCheckedPreference(title = R.string.profile_sms_title,
                                desc = R.string.profile_sms_desc,
                                checked = state.isGuideSMSMelody,
                                onClick = { viewModel.setWidgetGuideSMSMelody(!state.isGuideSMSMelody) }
        )
        DialogUncheckedVerticalPreference(enabled = state.isGuideSMSMelody,
                                          preImage = painterResource(id = R.drawable.symbol_sms),
                                          title = stringResource(id = R.string.profile_sms_melody),
                                          desc = if (state.isGuideSMSMelody)
                                              "${
                                                  stringResource(id = R.string.text_current)
                                              } ${
                                                  if (state.smsMelody == Uri.EMPTY)
                                                      stringResource(id = R.string.text_not_set)
                                                  else
                                                      getRingtoneTitle(context, state.smsMelody)
                                              }" else
                                              "${
                                                  stringResource(id = R.string.text_current)
                                              } ${
                                                  getRingtoneTitle(context,
                                                                   RingtoneManager.getActualDefaultRingtoneUri(context,
                                                                                                               RingtoneManager.TYPE_NOTIFICATION
                                                                   )
                                                  )
                                              }",
                                          onClick = {})
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }

}



