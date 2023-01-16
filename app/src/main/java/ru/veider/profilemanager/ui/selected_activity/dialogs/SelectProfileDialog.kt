package ru.veider.profilemanager.ui.selected_activity.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.selected_activity.dialogs.assets.SelectMode
import ru.veider.profilemanager.ui.theme.colorDay
import ru.veider.profilemanager.ui.theme.colorNight
import ru.veider.profilemanager.ui.theme.selectDialogModeFont
import ru.veider.profilemanager.ui.widget.assets.WidgetMode

@Composable
fun SelectProfileDialog(onDismiss: () -> Unit,
                        onAccept: (WidgetMode) -> Unit,
                        onStartPreferencesActivity: () -> Unit) {
    Dialog(onDismissRequest = onDismiss,
           properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
    ) {
        Card(shape = RoundedCornerShape(dimensionResource(id = R.dimen.single_padding)),
             modifier = Modifier
                 .padding(dimensionResource(id = R.dimen.single_padding)),
             elevation = dimensionResource(id = R.dimen.single_padding)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(0.8f)
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
                Divider(thickness = dimensionResource(id = R.dimen.double_spacer_width), modifier = Modifier.padding(end = dimensionResource(id = R.dimen.single_padding)))
                SelectMode(icon = R.drawable.widget_symbol_day, text = stringResource(id = R.string.mode_day_title),
                           color = colorDay,
                           action = {
                               onAccept(WidgetMode.DAY)
                           }
                )
                SelectMode(icon = R.drawable.widget_symbol_night, text = stringResource(id = R.string.mode_night_title),
                           color = colorNight,
                           action = {
                               onAccept(WidgetMode.NIGHT)
                           }
                )
            }
        }
    }
}


