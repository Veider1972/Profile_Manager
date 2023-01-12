package ru.veider.profilemanager.ui.selected_activity

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.PreferenceActivity
import ru.veider.profilemanager.ui.theme.*
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme
import ru.veider.profilemanager.ui.theme.selectDialogModeFont
import ru.veider.profilemanager.ui.widget.assets.WIDGET_MODE
import ru.veider.profilemanager.ui.widget.assets.WidgetMode
import ru.veider.profilemanager.viewmodel.PreferenceViewModel


class SelectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFinishOnTouchOutside(false)
        setContent {

            val viewModel: PreferenceViewModel = koinViewModel()

            ProfileManagerTheme {
                SelectProfileDialog(onDismiss = {
                    finish()
                },
                                    onAccept = {
                                        val updateIntent = Intent().apply {
                                            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                                            putExtra(WIDGET_MODE, it.name)
                                        }
                                        sendBroadcast(updateIntent)
                                        viewModel.setProfile(it)
                                        finish()
                                    },
                                    onStartPreferencesActivity = {
                                        val intent = Intent(this@SelectActivity, PreferenceActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    })


            }
        }
    }

}

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
                SelectMode(icon = R.drawable.mode_day, text = stringResource(id = R.string.mode_day_title),
                           color = colorDay,
                           action = {
                               onAccept(WidgetMode.DAY)
                           }
                )
                SelectMode(icon = R.drawable.mode_night, text = stringResource(id = R.string.mode_night_title),
                           color = colorNight,
                           action = {
                               onAccept(WidgetMode.NIGHT)
                           }
                )
            }
        }
    }
}


@Composable
fun SelectMode(icon: Int, text: String, color: Color, action: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier
            .weight(1f, true)
            .clickable {
                action()
            }) {
            Row() {
                Image(painter = painterResource(id = icon),
                      contentDescription = "",
                      modifier = Modifier
                          .height(30.dp)
                          .padding(end = dimensionResource(id = R.dimen.double_padding))
                )
                Text(text = text,
                     style = selectDialogModeFont,
                     color = color
                )
            }
        }
        Divider(color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )
        IconButton(onClick = { /*TODO*/ }) {
            Image(painter = painterResource(id = R.drawable.access_time), contentDescription = "", colorFilter = ColorFilter.tint(color = color))
        }
    }
}
