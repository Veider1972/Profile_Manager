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
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetColor
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun ProfileSetColor(color: WidgetColor,
                    onDismiss: () -> Unit,
                    onAccept: (WidgetColor) -> Unit,
                    onCancel: () -> Unit) {

    val viewModel: PreferenceViewModel = koinViewModel()

    val selectedColor = remember {
        mutableStateOf(color)
    }

    val colorList = arrayOf(arrayOf(WidgetColor.BLUE, WidgetColor.GREEN, WidgetColor.YELLOW),
                            arrayOf(WidgetColor.ORANGE, WidgetColor.RED, WidgetColor.PURPLE)
    )

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.dialog_profile_set_color_title))
        Column {
            for (row in colorList.indices)
                Row(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.single_padding))) {
                    for (column in colorList[row].indices)
                        CircleColor(rowScope = this,
                                    selectedColor = selectedColor.value,
                                    circleColor = colorList[row][column],
                                    onClick = {
                                        selectedColor.value = colorList[row][column]
                                    })
                }
            DialogAcceptCancelButtons(accept = {
                onAccept(selectedColor.value)
            }, cancel = { onCancel() })

        }
    }
}

@Composable
fun CircleColor(rowScope: RowScope, selectedColor: WidgetColor, circleColor: WidgetColor, onClick: () -> Unit) {
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