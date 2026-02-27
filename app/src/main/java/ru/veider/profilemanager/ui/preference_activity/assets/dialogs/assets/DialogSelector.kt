package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.dialogText

@Composable
fun DialogSelector(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.clickable { onCheckedChange(true) }) {
        Column(modifier = Modifier.weight(1f, true), verticalArrangement = Arrangement.Center) {
            Text(text = text, style = dialogText, maxLines = 1,
                 modifier = Modifier
                     .padding(start = dimensionResource(id = R.dimen.single_padding),
                              end = dimensionResource(id = R.dimen.single_padding)
                     )
//                     .weight(1f, true)
            )
        }

        Column(
            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.single_padding),
                                        bottom = dimensionResource(id = R.dimen.single_padding)
            ),
            verticalArrangement = Arrangement.Center
        ) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        }

    }
}