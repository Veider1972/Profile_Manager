package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.*

@Composable
fun DialogCheckedPreference(title: Int, desc: Int, checked: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier
            .weight(1f, true)
        ) {
            Text(text = stringResource(id = title), style = preferenceTitle, maxLines = 1,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
            Text(text = stringResource(id = desc), style = preferenceDesc,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
        }
        Checkbox(checked = checked, onCheckedChange = { onClick() })
    }
}

@Composable
fun DialogCheckedPreference(desc: Int, checked: Boolean, onClick: () -> Unit) {
    DialogCheckedPreference(desc = stringResource(id = desc), checked = checked, onClick = onClick)
}

@Composable
fun DialogCheckedPreference(desc: String, checked: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier
            .weight(1f, true)
        ) {
            Text(text = desc, style = preferenceTitle, maxLines = 1,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
        }
        Checkbox(checked = checked, onCheckedChange = { onClick() })
    }
}

@Composable
fun DialogCheckedPreference(enabled: Boolean, image: Painter, desc: Int, checked: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable(enabled = enabled, onClick = onClick)
        .fillMaxWidth()
        .padding(bottom = dimensionResource(id = R.dimen.half_padding))
    ) {
        Icon(painter = image,
             contentDescription = "",
             modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding)),
             tint = getIconColor(enabled)
        )
        Text(text = stringResource(id = desc), style = preferenceTitle,
             modifier = Modifier
                 .padding(start = dimensionResource(id = R.dimen.single_padding),
                          end = dimensionResource(id = R.dimen.single_padding)
                 )
                 .weight(1f, true),
             color = getFontColor(enabled)
        )
        Box(modifier = Modifier
            .padding(end = dimensionResource(id = R.dimen.single_padding))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(dimensionResource(id = R.dimen.half_padding))) {
                if (checked) {
                    Text("       ", modifier = Modifier
                        .border(1.dp, getColor(enabled, true), RectangleShape)
                    )
                    Text(text = " ${stringResource(id = R.string.text_yes)} ", modifier = Modifier
                        .border(1.dp, getColor(enabled, true), RectangleShape)
                        .background(getColor(enabled, true)),
                         color = getFontColor(enabled)
                    )
                } else {
                    Text(text = " ${stringResource(id = R.string.text_no)} ", modifier = Modifier
                        .border(1.dp, getColor(enabled, false), RectangleShape)
                        .background(getColor(enabled, false)),
                         color = getFontColor(enabled)
                    )
                    Text("      ",
                         modifier = Modifier
                             .border(1.dp, getColor(enabled, false), RectangleShape)
                    )
                }

//                Icon(painter = painterResource(id = if (checked) R.drawable.button_accept else R.drawable.button_cancel),
//                     contentDescription = "",
//                     tint = getColor(enabled, checked)
//                )
//                Text(text = stringResource(id = if (checked) R.string.text_yes else R.string.text_no), color = getColor(enabled, checked))
            }

        }
    }
}

@Composable
fun getColor(enabled: Boolean, checked: Boolean): Color =
        if (enabled) {
            if (checked)
                MaterialTheme.colors.secondary
            else
                colorGray
        } else
            colorInactive

@Composable
fun getFontColor(enabled: Boolean): Color =
        if (enabled) {
            MaterialTheme.colors.onSurface
        } else
            colorGray

@Composable
fun getIconColor(enabled: Boolean): Color =
        if (enabled) {
            MaterialTheme.colors.onSurface
        } else
            colorInactive
