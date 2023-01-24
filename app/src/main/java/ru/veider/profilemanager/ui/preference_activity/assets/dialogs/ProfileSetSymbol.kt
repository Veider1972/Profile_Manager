package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetSymbol
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun ProfileSetSymbol(onDismiss: () -> Unit,
                     onAccept: (WidgetSymbol) -> Unit,
                     onCancel: () -> Unit) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileState.collectAsState()

    val ringSymbol = remember {
        mutableStateOf(state.symbol)
    }

    val symbolList = arrayOf(arrayOf(WidgetSymbol.DAY, WidgetSymbol.NIGHT, WidgetSymbol.HOME),
                             arrayOf(WidgetSymbol.OFFICE, WidgetSymbol.ALOUD, WidgetSymbol.SILENT)
    )

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.dialog_profile_set_color_title))
        Column {
            for (row in symbolList.indices)
                Row(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.single_padding))) {
                    for (column in symbolList[row].indices)
                        Symbol(rowScope = this,
                               selectedSymbol = ringSymbol.value,
                               symbol = symbolList[row][column],
                               onClick = {
                                   ringSymbol.value = symbolList[row][column]
                               })
                }
            DialogAcceptCancelButtons(accept = {
                onAccept(ringSymbol.value)
            }, cancel = { onCancel() })

        }
    }
}

@Composable
fun Symbol(rowScope: RowScope, selectedSymbol: WidgetSymbol, symbol: WidgetSymbol, onClick: () -> Unit) {
    rowScope.run {
        Icon(painter = painterResource(id = symbol.imageId),
             contentDescription = "",
             modifier = Modifier
                 .weight(1f, true)
                 .scale(if (selectedSymbol == symbol) 1.2f else 1f)
                 .clickable(onClick = onClick),
             tint = if (selectedSymbol == symbol) MaterialTheme.colors.primary else Color.Unspecified
        )
    }
}