package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.domain.preference.WidgetColor
import ru.veider.profilemanager.domain.preference.WidgetSymbol
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper

@Composable
fun ProfileSetSymbol(
    symbol: WidgetSymbol,
    onDismiss: () -> Unit,
    onAccept: (WidgetSymbol) -> Unit,
    onCancel: () -> Unit
) {

    var ringSymbol by remember {
        mutableStateOf(symbol)
    }

    val symbolList = arrayOf(
        arrayOf(WidgetSymbol.DAY, WidgetSymbol.NIGHT, WidgetSymbol.HOME),
        arrayOf(WidgetSymbol.OFFICE, WidgetSymbol.ALOUD, WidgetSymbol.SILENT)
    )

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.dialog_profile_set_color_title))
        Column {
            for (row in symbolList.indices)
                Row(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.single_padding))) {
                    for (column in symbolList[row].indices)
                        Symbol(
                            rowScope = this,
                            selectedSymbol = ringSymbol,
                            symbol = symbolList[row][column],
                            onClick = {
                                ringSymbol = symbolList[row][column]
                            })
                }
            Row(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.single_padding))) {
                Symbol(
                    rowScope = this,
                    selectedSymbol = ringSymbol,
                    symbol = WidgetSymbol.CAR,
                    onClick = {
                        ringSymbol = WidgetSymbol.CAR
                    })
            }
            DialogAcceptCancelButtons(accept = {
                onAccept(ringSymbol)
            }, cancel = { onCancel() })

        }
    }
}

@Composable
fun Symbol(rowScope: RowScope, selectedSymbol: WidgetSymbol, symbol: WidgetSymbol, onClick: () -> Unit) {
    rowScope.run {
        Icon(
            painter = painterResource(id = symbol.imageId(WidgetColor.BLUE)),
            contentDescription = "",
            modifier = Modifier
                .weight(1f, true)
                .scale(if (selectedSymbol == symbol) 1.2f else 1f)
                .clickable(onClick = onClick),
            tint = if (selectedSymbol == symbol) MaterialTheme.colorScheme.primary else Color.Unspecified
        )
    }
}