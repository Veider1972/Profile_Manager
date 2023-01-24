package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogSelector
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetBackgroundColor
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun BackgroundColorSetDialog(onDismiss: () -> Unit,
                             onAccept: (WidgetBackgroundColor) -> Unit,
                             onCancel: () -> Unit) {
    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.widgetState.collectAsState()

    val backgroundColor = rememberSaveable {
        mutableStateOf(state.backgroundColor)
    }

    DialogWrapper(onDismiss = onDismiss) {
        Column() {
            DialogPreferenceTitle(text = stringResource(id = R.string.widget_background_color_title))
            DialogSelector(text = WidgetBackgroundColor.WHITE.desc,
                           checked = (backgroundColor.value == WidgetBackgroundColor.WHITE),
                           onCheckedChange = { backgroundColor.value = WidgetBackgroundColor.WHITE })
            DialogSelector(text = WidgetBackgroundColor.GREY.desc,
                           checked = (backgroundColor.value == WidgetBackgroundColor.GREY),
                           onCheckedChange = { backgroundColor.value = WidgetBackgroundColor.GREY })
            DialogSelector(text = WidgetBackgroundColor.BLACK.desc,
                           checked = (backgroundColor.value == WidgetBackgroundColor.BLACK),
                           onCheckedChange = { backgroundColor.value = WidgetBackgroundColor.BLACK })
            DialogSelector(text = WidgetBackgroundColor.NONE.desc,
                           checked = (backgroundColor.value == WidgetBackgroundColor.NONE),
                           onCheckedChange = { backgroundColor.value = WidgetBackgroundColor.NONE })
            DialogAcceptCancelButtons(accept = {
                onAccept(backgroundColor.value)
            }, cancel = { onCancel() })
        }
    }
}