package ru.veider.profilemanager.ui.preference_activity.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.preferenceCaption
import ru.veider.profilemanager.ui.theme.preferenceDesc
import ru.veider.profilemanager.ui.theme.preferenceTitle

@Composable
fun SetCaption(text: Int) {
    Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.single_padding))) {
        Text(text = stringResource(id = text), style = preferenceCaption,
             modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding))
        )
        HorizontalThickDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }

}

@Composable
fun SetCheckedPreference(title: Int, desc: Int, checked: Boolean, onCheckedChange: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable {
            onCheckedChange()
        }) {
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
        Checkbox(checked = checked, onCheckedChange = { onCheckedChange() })
    }
}

@Composable
fun SetUncheckedPreference(title: Int, desc: Int, action: () -> Unit) =
        SetUncheckedPreference(title = stringResource(id = title), desc = stringResource(id = desc), action = { action() })

@Composable
fun SetUncheckedPreference(title: String, desc: String, action: () -> Unit) {
    Row(modifier = Modifier
        .clickable {
            action()
        }
        .padding(vertical = dimensionResource(id = R.dimen.half_padding))) {
        Column {
            Text(text = title, style = preferenceTitle, maxLines = 1,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
            Text(text = desc, style = preferenceDesc,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
        }
    }
}



