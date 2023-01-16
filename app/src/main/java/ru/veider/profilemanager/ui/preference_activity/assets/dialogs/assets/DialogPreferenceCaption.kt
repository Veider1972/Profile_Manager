package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.preferenceCaption

@Composable
fun DialogPreferenceCaption(text: Int) {
    Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.single_padding))) {
        Text(text = stringResource(id = text).uppercase(), style = preferenceCaption,
             modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding))
        )
        DialogHorizontalThickDivider(padding = dimensionResource(id = R.dimen.single_padding))
    }

}