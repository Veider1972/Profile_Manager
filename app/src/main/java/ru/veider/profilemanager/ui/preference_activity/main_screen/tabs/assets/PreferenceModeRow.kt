package ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.assets

import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DrawWidget
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetBackground
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetSymbol
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetColor
import ru.veider.profilemanager.ui.theme.colorInactive
import ru.veider.profilemanager.ui.theme.selectDialogModeFont

@Composable
fun PreferenceModeRow(widgetBackground: WidgetBackground, ringColor: WidgetColor, symbol: WidgetSymbol, symbolColor: WidgetColor, text: String,
                      textColor: Color, runMode: () -> Unit, timeMode: () -> Unit,
                      editMode: () -> Unit) {

    val playPressed = rememberSaveable { mutableStateOf(false) }
    val timerPressed = rememberSaveable { mutableStateOf(false) }
    val editPressed = rememberSaveable { mutableStateOf(false) }

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
        Chip(imageId = R.drawable.run_mode, color = textColor, onClick = runMode, modifier = Modifier.scale(0.8f))
        Divider(color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )


        Chip(imageId = R.drawable.access_time, color = textColor, onClick = timeMode, modifier = Modifier.scale(0.8f))
        Divider(color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(30.dp)
                    .width(dimensionResource(id = R.dimen.spacer_width))
        )
        Chip(imageId = R.drawable.edit_mode, color = textColor, onClick = editMode, modifier = Modifier.scale(0.8f))
    }
}

@Composable
private fun Chip(
    imageId: Int,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val pressed = rememberSaveable { mutableStateOf(false) }

    IconButton(onClick = {
        pressed.value = true
        onClick()
        CoroutineScope(Dispatchers.Main).launch {
            delay(400)
            pressed.value = false
        }
    }) {
        Surface(
            color = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.surface,
            shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = colorInactive
        ),
            elevation = if (pressed.value) dimensionResource(id = R.dimen.single_elevation) else dimensionResource(id = R.dimen.double_elevation),
            modifier = modifier
        ) {
            Row(modifier = Modifier) {
                Icon(
                    painter = painterResource(imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(20.dp),
                    tint = color
                )
            }
        }
    }
}
