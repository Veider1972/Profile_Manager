package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import android.content.Context
import android.media.RingtoneManager
import android.media.RingtoneManager.*
import android.net.Uri
import android.os.Build
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogCheckedPreference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileSetRingtone(mediaType: Int,
                       onDismiss: () -> Unit,
                       onAccept: (Uri) -> Unit,
                       onCancel: () -> Unit) {
    val context = LocalContext.current

    val ringtoneList by lazy { getAndroidMedia(context, mediaType) }

    val checkedTitle = rememberSaveable { mutableStateOf("") }
    val checkedUri = rememberSaveable { mutableStateOf(Uri.EMPTY) }

    val ringtone = remember { mutableStateOf(getRingtone(context, Uri.EMPTY)) }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.dialog_profile_set_ringtone_title))
        LazyColumn(modifier = Modifier.aspectRatio(0.7f)) {
            item {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement.provides(false)) {
                    Row(modifier = Modifier.padding(end = dimensionResource(id = R.dimen.single_padding),
                                                    bottom = dimensionResource(id = R.dimen.single_padding)
                    )
                    ) {
                        DialogCheckedPreference(desc = stringResource(id = R.string.dialog_profile_set_ringtone_silent),
                                                checked = (stringResource(id = R.string.dialog_profile_set_ringtone_silent) == checkedTitle.value),
                                                onClick = {
                                                    checkedTitle.value = context.getString(R.string.dialog_profile_set_ringtone_silent)
                                                    checkedUri.value = Uri.EMPTY
                                                    ringtone.value.stop()
                                                })
                    }

                }
            }
            ringtoneList.forEach {
                item {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement.provides(false)) {
                        Row(modifier = Modifier.padding(end = dimensionResource(id = R.dimen.single_padding),
                                                        bottom = dimensionResource(id = R.dimen.single_padding)
                        )
                        ) {
                            DialogCheckedPreference(desc = it.title,
                                                    checked = (it.title == checkedTitle.value),
                                                    onClick = {
                                                        checkedTitle.value = it.title
                                                        checkedUri.value = it.uri

                                                        ringtone.value.stop()
                                                        ringtone.value = getRingtone(context, it.uri)
                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                                                            ringtone.value.isLooping = false
                                                        ringtone.value.play()
                                                    })
                        }

                    }
                }
            }
        }
        DialogAcceptCancelButtons(
            accept = {
                ringtone.value.stop()
                onAccept(checkedUri.value)
            },
            cancel = onCancel
        )
    }
}

private data class Ringtone(val title: String, val uri: Uri)

private fun getAndroidMedia(context: Context, mediaType: Int): List<Ringtone> {
    if (mediaType != TYPE_NOTIFICATION && mediaType != TYPE_ALARM && mediaType != TYPE_RINGTONE)
        throw Throwable(context.getString(R.string.error_media_type))
    val ringtoneManager = RingtoneManager(context)
    ringtoneManager.setType(mediaType)
    val cursor = ringtoneManager.cursor
    return (0 until cursor.count).map {
        cursor.moveToPosition(it)
        Ringtone(
            title = cursor.getString(TITLE_COLUMN_INDEX),
            uri = ringtoneManager.getRingtoneUri(it)
        )
    }
}