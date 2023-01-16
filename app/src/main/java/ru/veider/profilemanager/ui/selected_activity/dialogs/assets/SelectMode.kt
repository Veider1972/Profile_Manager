package ru.veider.profilemanager.ui.selected_activity.dialogs.assets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.selectDialogModeFont

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