package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.preferenceDesc

@Composable
fun DialogPreferenceDesc(desc: Int) {
    DialogPreferenceDesc(desc = stringResource(id = desc))
}

@Composable
fun DialogPreferenceDesc(desc: String) {
    Row() {
        Text(text = desc, style = preferenceDesc,
             modifier = Modifier
                 .padding(start = dimensionResource(id = R.dimen.single_padding))
        )
    }
}