package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.*

@Composable
fun DialogCheckedPreference(title: Int, desc: Int, checked: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable(onClick = onClick)) {
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
fun DialogCheckedPreference(enabled: Boolean, image: Painter, desc: Int, checked: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable(enabled = enabled, onClick = onClick)
        .fillMaxWidth()
    ) {
        Icon(painter = image,
             contentDescription = "",
             modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding)),
             tint = if (enabled) MaterialTheme.colors.onSurface else colorInactive
        )
        Text(text = stringResource(id = desc), style = preferenceTitle,
             modifier = Modifier
                 .padding(start = dimensionResource(id = R.dimen.single_padding),
                          end = dimensionResource(id = R.dimen.single_padding)
                 )
                 .weight(1f, true),
             color = if (enabled) MaterialTheme.colors.onSurface else colorInactive
        )
        Checkbox(checked = checked,
                 onCheckedChange = { if (enabled) onClick() },
                 colors = CheckboxDefaults.colors(disabledColor = colorInactive),
                 enabled = enabled
        )
    }
}