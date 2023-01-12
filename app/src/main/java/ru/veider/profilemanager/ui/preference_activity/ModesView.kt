package ru.veider.profilemanager.ui.preference_activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.colorDay
import ru.veider.profilemanager.ui.theme.colorNight
import ru.veider.profilemanager.ui.theme.selectDialogModeFont

@Composable
fun ModesView() {
    Column(modifier = Modifier
        .padding(start = dimensionResource(id = R.dimen.double_padding),
                 bottom = dimensionResource(id = R.dimen.single_padding)
        )
        .fillMaxSize()
    ) {

        PreferenceMode(icon = R.drawable.mode_day, text = stringResource(id = R.string.mode_day_title), color = colorDay,
                       runMode = { /*TODO*/ }, timeMode = { /*TODO*/ }, editMode = { /*TODO*/ })
        PreferenceMode(icon = R.drawable.mode_night, text = stringResource(id = R.string.mode_night_title), color = colorNight,
                       runMode = { /*TODO*/ }, timeMode = { /*TODO*/ }, editMode = { /*TODO*/ })
    }
}

@Composable
fun PreferenceMode(icon: Int, text: String, color: Color, runMode: () -> Unit, timeMode: () -> Unit, editMode: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.single_padding))
    ) {
        Column(modifier = Modifier
            .weight(1f, true)
        ) {
            Row() {
                Image(painter = painterResource(id = icon),
                      contentDescription = "",
                      modifier = Modifier
                          .height(30.dp)
                          .padding(end = dimensionResource(id = R.dimen.double_padding))
                )
                Text(
                    text = text,
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
        IconButton(onClick = { runMode() }) {
            Image(painter = painterResource(id = R.drawable.run_mode), contentDescription = "", colorFilter = ColorFilter.tint(color=color))
        }
        Divider(color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )
        IconButton(onClick = { timeMode() }) {
            Image(painter = painterResource(id = R.drawable.access_time), contentDescription = "", colorFilter = ColorFilter.tint(color=color))
        }
        Divider(color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )
        IconButton(onClick = { editMode() }) {
            Image(painter = painterResource(id = R.drawable.edit_mode), contentDescription = "", colorFilter = ColorFilter.tint(color=color))
        }

    }
}