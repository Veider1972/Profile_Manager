package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.dialogTitle

@Composable
fun DialogPreferenceTitle(text: Int){
    DialogPreferenceTitle(stringResource(id = text))
}

@Composable
fun DialogPreferenceTitle(text: String) {
    Text(text = text.uppercase(), style = dialogTitle, maxLines = 1,
         modifier = Modifier
             .padding(dimensionResource(id = R.dimen.single_padding))
             .fillMaxWidth()
    )
    Divider(thickness = dimensionResource(id = R.dimen.double_spacer_width),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.single_padding)),
            color = MaterialTheme.colors.primary
    )
}