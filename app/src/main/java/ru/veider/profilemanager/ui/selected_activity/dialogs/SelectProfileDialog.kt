package ru.veider.profilemanager.ui.selected_activity.dialogs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.state.ProfileState
import ru.veider.profilemanager.ui.selected_activity.dialogs.assets.SelectMode
import ru.veider.profilemanager.ui.theme.selectDialogModeFont
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectProfileDialog(onDismiss: () -> Unit,
                        onAccept: (ProfileState) -> Unit,
                        onStartPreferencesActivity: () -> Unit) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val profiles by viewModel.profilesState.collectAsState()
    val widget by viewModel.widgetState.collectAsState()

    Dialog(onDismissRequest = onDismiss,
           properties = DialogProperties(dismissOnClickOutside = false,
                                         dismissOnBackPress = false,
                                         securePolicy = SecureFlagPolicy.SecureOff,
                                         decorFitsSystemWindows = false,
                                         usePlatformDefaultWidth = false
           )
    ) {
        Card(shape = RoundedCornerShape(dimensionResource(id = R.dimen.single_padding)),
             modifier = Modifier
                 .padding(dimensionResource(id = R.dimen.single_padding))
                 .wrapContentHeight()
                 .wrapContentWidth(),
             elevation = dimensionResource(id = R.dimen.single_padding)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(start = dimensionResource(id = R.dimen.single_padding),
                         bottom = dimensionResource(id = R.dimen.single_padding)
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(id = R.string.select_mode_title),
                         modifier = Modifier.weight(1f, true),
                         style = selectDialogModeFont
                    )
                    IconButton(onClick = {
                        onStartPreferencesActivity()
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.run_preferences),
                            contentDescription = ""
                        )
                    }
                }
                Divider(thickness = dimensionResource(id = R.dimen.double_spacer_width),
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.single_padding))
                )

                profiles.data.forEach {
                    SelectMode(widgetBackground = widget.backgroundColor,
                               ringColor = it.ringColor,
                               symbol = it.symbol,
                               symbolColor = it.symbolColor,
                               text = it.name,
                               timerColor = it.symbolColor.color,
                               action = {
                                   onAccept(it)
                               }
                    )
                }
            }
        }
    }
}


