package ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs

import android.media.RingtoneManager
import android.net.Uri
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
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetRingtone
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.ProfileSetVolume
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.*
import ru.veider.profilemanager.ui.preference_activity.assets.getRingtoneTitle
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun SoundsTab(navController: NavController) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileState.collectAsState()
    val context = LocalContext.current

    var showProfileSetVolumeDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileSetVolumeDialog)
        ProfileSetVolume(
            onDismiss = { showProfileSetVolumeDialog = false },
            onAccept = { commonVolume, useCommonVolume, notificationVolume, mediaVolume, alarmVolume ->
                viewModel.setProfileVolume(commonVolume, useCommonVolume, notificationVolume, mediaVolume, alarmVolume)
                showProfileSetVolumeDialog = false
            },
            onCancel = { showProfileSetVolumeDialog = false })

    var showProfileRingtonePickerDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileRingtonePickerDialog)
        ProfileSetRingtone(
            mediaType = RingtoneManager.TYPE_RINGTONE,
            onDismiss = { showProfileRingtonePickerDialog = false },
            onAccept = {
                viewModel.setProfileCallMelody(it)
                showProfileRingtonePickerDialog = false
            },
            onCancel = { showProfileRingtonePickerDialog = false })

    var showProfileNotificationPickerDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileNotificationPickerDialog)
        ProfileSetRingtone(
            mediaType = RingtoneManager.TYPE_NOTIFICATION,
            onDismiss = { showProfileNotificationPickerDialog = false },
            onAccept = {
                viewModel.setProfileSMSMelody(it)
                showProfileNotificationPickerDialog = false
            },
            onCancel = { showProfileNotificationPickerDialog = false })

    var showProfileAlarmPickerDialog by rememberSaveable { mutableStateOf(false) }
    if (showProfileAlarmPickerDialog)
        ProfileSetRingtone(
            mediaType = RingtoneManager.TYPE_ALARM,
            onDismiss = { showProfileAlarmPickerDialog = false },
            onAccept = {
                viewModel.setProfileAlarmMelody(it)
                showProfileAlarmPickerDialog = false
            },
            onCancel = { showProfileAlarmPickerDialog = false })

    Column(modifier = Modifier.fillMaxSize()) {
        // Управление вибрацией
        DialogCheckedPreference(title = R.string.profile_vibration_title,
                                desc = R.string.profile_vibration_desc,
                                checked = state.guideVibration,
                                onClick = { viewModel.setProfileGuideVibration(!state.guideVibration) }
        )
        DialogCheckedPreference(enabled = state.guideVibration,
                                image = painterResource(id = R.drawable.symbol_vibration),
                                desc = R.string.profile_vibration,
                                checked = state.vibrationOn,
                                onClick = { viewModel.setProfileVibration(!state.vibrationOn) })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление громкостью вызовов
        DialogCheckedPreference(title = R.string.profile_volume_title,
                                desc = R.string.profile_volume_desc,
                                checked = state.guideVolume,
                                onClick = { viewModel.setProfileGuideVolume(!state.guideVolume) }
        )
        DialogUncheckedHorizontalPreference(enabled = state.guideVolume,
                                            preImage = painterResource(id = R.drawable.symbol_aloud),
                                            title = stringResource(id = R.string.profile_volume),
                                            desc = if (state.useCommonVolume)
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
                                                }%, ${
                                                    state.mediaVolume
                                                }%, ${
                                                    state.alarmVolume
                                                }%",
                                            onClick = { showProfileSetVolumeDialog = true })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление мелодией вызова
        DialogCheckedPreference(title = R.string.profile_call_melody_title,
                                desc = R.string.profile_call_melody_desc,
                                checked = state.guideCallMelody,
                                onClick = { viewModel.setProfileGuideCallMelody(!state.guideCallMelody) }
        )
        DialogUncheckedVerticalPreference(enabled = state.guideCallMelody,
                                          preImage = painterResource(id = R.drawable.symbol_calls),
                                          title = stringResource(id = R.string.profile_call_melody),
                                          desc = if (state.guideCallMelody)
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
                                          onClick = {
                                              showProfileRingtonePickerDialog = true
                                          })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление звуком уведомлений
        DialogCheckedPreference(title = R.string.profile_sms_title,
                                desc = R.string.profile_sms_desc,
                                checked = state.guideSMSMelody,
                                onClick = { viewModel.setProfileGuideSMSMelody(!state.guideSMSMelody) }
        )
        DialogUncheckedVerticalPreference(enabled = state.guideSMSMelody,
                                          preImage = painterResource(id = R.drawable.symbol_sms),
                                          title = stringResource(id = R.string.profile_sms_melody),
                                          desc = if (state.guideSMSMelody)
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
                                          onClick = {
                                              showProfileNotificationPickerDialog = true
                                          })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
        // Управление звуком будильника
        DialogCheckedPreference(title = R.string.profile_alarm_title,
                                desc = R.string.profile_alarm_desc,
                                checked = state.guideAlarmMelody,
                                onClick = { viewModel.setProfileGuideAlarmMelody(!state.guideAlarmMelody) }
        )
        DialogUncheckedVerticalPreference(enabled = state.guideAlarmMelody,
                                          preImage = painterResource(id = R.drawable.symbol_alarm),
                                          title = stringResource(id = R.string.profile_alarm_melody),
                                          desc = if (state.guideAlarmMelody)
                                              "${
                                                  stringResource(id = R.string.text_current)
                                              } ${
                                                  if (state.smsMelody == Uri.EMPTY)
                                                      stringResource(id = R.string.text_not_set)
                                                  else
                                                      getRingtoneTitle(context, state.alarmMelody)
                                              }" else
                                              "${
                                                  stringResource(id = R.string.text_current)
                                              } ${
                                                  getRingtoneTitle(context,
                                                                   RingtoneManager.getActualDefaultRingtoneUri(context,
                                                                                                               RingtoneManager.TYPE_ALARM
                                                                   )
                                                  )
                                              }",
                                          onClick = {
                                              showProfileAlarmPickerDialog = true
                                          })
        DialogHorizontalThingDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }

}



