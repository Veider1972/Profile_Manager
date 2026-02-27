package ru.veider.profilemanager.ui.selected_activity.dialogs.assets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DrawWidget
import ru.veider.profilemanager.domain.preference.WidgetBackground
import ru.veider.profilemanager.domain.preference.WidgetColor
import ru.veider.profilemanager.domain.preference.WidgetSymbol
import ru.veider.profilemanager.ui.theme.selectDialogModeFont

@Composable
fun SelectMode(widgetBackground: WidgetBackground, ringColor: WidgetColor, symbol: WidgetSymbol, symbolColor: WidgetColor, text: String, timerColor: Color, action: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier
            .weight(1f, true)
            .clickable {
                action()
            }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
//                DrawWidget(size = 70.dp, widgetBackground = widgetBackground, ringColor = ringColor, symbol = symbol, symbolColor = symbolColor)
                Text(text = text,
                     style = selectDialogModeFont,
                     color = timerColor
                )
            }
        }
        Divider(color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )
        IconButton(onClick = { /*TODO*/ }) {
            Image(painter = painterResource(id = R.drawable.access_time), contentDescription = "", colorFilter = ColorFilter.tint(color = timerColor))
        }
    }
}