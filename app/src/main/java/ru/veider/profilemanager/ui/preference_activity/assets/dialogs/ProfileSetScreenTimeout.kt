package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.preference_activity.assets.toDp
import ru.veider.profilemanager.ui.theme.dialogTimeMinSec
import ru.veider.profilemanager.ui.theme.dialogTimeSelector

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ProfileSetScreenTimeout(currentTime: Pair<Int, Int>,
                            onDismiss: () -> Unit,
                            onAccept: (Int) -> Unit,
                            onCancel: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val times = arrayListOf("15 ${stringResource(id = R.string.text_seconds)}",
                            "30 ${stringResource(id = R.string.text_seconds)}",
                            "1 ${stringResource(id = R.string.text_minutes)}",
                            "2 ${stringResource(id = R.string.text_minutes)}",
                            "5 ${stringResource(id = R.string.text_minutes)}",
                            stringResource(id = R.string.text_continuously),
                            stringResource(id = R.string.text_custom)

    )


    fun getComboBoxText(value: Int): String =
            when (value) {
                15   -> times[0]
                30   -> times[1]
                60   -> times[2]
                120  -> times[3]
                300  -> times[4]
                0    -> times[5]
                else -> times[6]
            }

    var expanded by remember { mutableStateOf(false) }

    val minState = rememberLazyListState()
    val minFling = rememberSnapFlingBehavior(minState)
    val secState = rememberLazyListState()
    val secFling = rememberSnapFlingBehavior(secState)

    val currTime = remember { derivedStateOf { minState.firstVisibleItemIndex * 60 + secState.firstVisibleItemIndex } }

    fun selectTime(min: Int, sec: Int) {
        scope.launch { minState.animateScrollToItem(min, context.resources.getInteger(R.integer.time_selector_offset)) }
        scope.launch { secState.animateScrollToItem(sec, context.resources.getInteger(R.integer.time_selector_offset)) }
    }

    LaunchedEffect(Unit) {
        selectTime(currentTime.first, currentTime.second)
    }

    val height = remember { mutableStateOf(dialogTimeSelector.fontSize.value.toDp(context)) }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.dialog_profile_set_screen_timeout_title))
        ExposedDropdownMenuBox(expanded = expanded,
                               onExpandedChange = { expanded = !expanded },
                               modifier = Modifier.padding(start = dimensionResource(id = R.dimen.single_padding))
        ) {
            TextField(value = getComboBoxText(currTime.value),
                      onValueChange = {},
                      trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                      readOnly = true
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                times.forEach { text ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        when (text) {
                            getComboBoxText(15)  -> selectTime(0, 15)
                            getComboBoxText(30)  -> selectTime(0, 30)
                            getComboBoxText(60)  -> selectTime(1, 0)
                            getComboBoxText(120) -> selectTime(2, 0)
                            getComboBoxText(300) -> selectTime(5, 0)
                            getComboBoxText(0)   -> selectTime(0, 0)
                        }
                    }) {
                        Text(text = text)
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            LazyColumn(state = minState,
                       flingBehavior = minFling,
                       reverseLayout = true,
                       contentPadding = PaddingValues(0.dp),
                       verticalArrangement = Arrangement.Bottom,
                       modifier = Modifier.height(height.value)
            ) {
                (0..99).forEach {
                    item {
                        Text(text = String.format("%02d", it), style = dialogTimeSelector)
                    }
                }
            }
            Text(stringResource(id = R.string.text_minutes),
                 style = dialogTimeMinSec,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.half_padding),
                                             end = dimensionResource(id = R.dimen.single_padding),
                                             bottom = 4.dp
                 )
            )
            LazyColumn(state = secState,
                       flingBehavior = secFling,
                       reverseLayout = true,
                       contentPadding = PaddingValues(0.dp),
                       modifier = Modifier.height(height.value)
            ) {
                (0..59).forEach {
                    item {
                        Text(text = String.format("%02d", it), style = dialogTimeSelector)
                    }
                }
            }
            Text(stringResource(id = R.string.text_seconds),
                 style = dialogTimeMinSec,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.half_padding),
                                             bottom = 4.dp
                 )
            )
        }
        DialogAcceptCancelButtons(
            accept = {
                onAccept(currTime.value)
            },
            cancel = { onCancel() })
    }
}