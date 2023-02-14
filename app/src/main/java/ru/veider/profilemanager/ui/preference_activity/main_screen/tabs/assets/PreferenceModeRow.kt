package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.assets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DrawWidget
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetBackground
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetSymbol
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetColor
import ru.veider.profilemanager.ui.theme.selectDialogModeFont

@Composable
fun PreferenceModeRow(widgetBackground: WidgetBackground, ringColor: WidgetColor, symbol: WidgetSymbol, symbolColor: WidgetColor, text: String, textColor: Color, runMode: () -> Unit, timeMode: () -> Unit,
                      editMode: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.single_padding))
    ) {
        Column(modifier = Modifier
            .weight(1f, true)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                DrawWidget(widgetBackground = widgetBackground, ringColor = ringColor, symbol = symbol, symbolColor = symbolColor)
                Text(
                    text = text,
                    style = selectDialogModeFont,
                    color = textColor
                )
            }
        }
        Divider(color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )
        IconButton(onClick = { runMode() }) {
            Image(painter = painterResource(id = R.drawable.run_mode), contentDescription = "", colorFilter = ColorFilter.tint(color = textColor))
        }
        Divider(color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )
        IconButton(onClick = { timeMode() }) {
            Image(painter = painterResource(id = R.drawable.access_time), contentDescription = "", colorFilter = ColorFilter.tint(color = textColor))
        }
        Divider(color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )
        IconButton(onClick = { editMode() }) {
            Image(painter = painterResource(id = R.drawable.edit_mode), contentDescription = "", colorFilter = ColorFilter.tint(color = textColor))
        }

    }
}