package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetSymbolColor
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun ProfileSetColor(onDismiss: () -> Unit,
                    onAccept: (WidgetSymbolColor) -> Unit,
                    onCancel: () -> Unit) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileState.collectAsState()

    val ringColor = remember {
        mutableStateOf(state.ringColor)
    }

    val colorList = arrayOf(arrayOf(WidgetSymbolColor.BLUE, WidgetSymbolColor.GREEN, WidgetSymbolColor.YELLOW),
                            arrayOf(WidgetSymbolColor.ORANGE, WidgetSymbolColor.RED, WidgetSymbolColor.PURPLE)
    )

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.dialog_profile_set_color_title))
        Column {
            for (row in colorList.indices)
                Row(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.single_padding))) {
                    for (column in colorList[row].indices)
                        CircleColor(rowScope = this,
                                    selectedColor = ringColor.value,
                                    circleColor = colorList[row][column],
                                    onClick = {
                                        ringColor.value = colorList[row][column]
                                    })
                }
            DialogAcceptCancelButtons(accept = {
                onAccept(ringColor.value)
            }, cancel = { onCancel() })

        }
    }
}

@Composable
fun CircleColor(rowScope: RowScope, selectedColor: WidgetSymbolColor, circleColor: WidgetSymbolColor, onClick: () -> Unit) {
    rowScope.run {
        Icon(painter = painterResource(id = R.drawable.symbol_circle),
             contentDescription = "",
             tint = circleColor.color,
             modifier = Modifier
                 .weight(1f, true)
                 .scale(if (selectedColor == circleColor) 1.5f else 1f)
                 .clickable(onClick = onClick)
        )
    }
}