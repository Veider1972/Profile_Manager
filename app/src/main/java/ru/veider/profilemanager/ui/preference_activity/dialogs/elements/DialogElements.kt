package ru.veider.profilemanager.ui.preference_activity.dialogs.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.dialogText
import ru.veider.profilemanager.ui.theme.dialogTitle

@Composable
fun DialogTitle(text: String) {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogSelector(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.clickable { onCheckedChange(true) }) {
        Column(modifier = Modifier.weight(1f,true), verticalArrangement = Arrangement.Center) {
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
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement.provides(false)) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange)
            }
        }

    }
}

@Composable
fun DialogAcceptCancelButtons(accept: () -> Unit, cancel: () -> Unit) {
    Row() {
        Button(onClick = accept,
               modifier = Modifier
                   .padding(start = dimensionResource(id = R.dimen.single_padding),
                            bottom = dimensionResource(id = R.dimen.single_padding),
                            end = dimensionResource(id = R.dimen.half_padding)
                   )
                   .weight(0.5f)
        ) {
            Text(text = stringResource(id = R.string.accept))
        }
        Button(onClick = cancel,
               modifier = Modifier
                   .padding(start = dimensionResource(id = R.dimen.half_padding),
                            end = dimensionResource(id = R.dimen.single_padding),
                            bottom = dimensionResource(id = R.dimen.single_padding)
                   )
                   .weight(0.5f)
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }
}