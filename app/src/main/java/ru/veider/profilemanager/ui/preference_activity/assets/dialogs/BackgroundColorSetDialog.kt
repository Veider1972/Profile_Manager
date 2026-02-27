package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogSelector
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.domain.preference.WidgetBackground
import ru.veider.profilemanager.repo.Preference

@Composable
fun BackgroundColorSetDialog(
    onDismiss: () -> Unit,
    onAccept: (WidgetBackground) -> Unit,
    onCancel: () -> Unit
) {
    val prefs: Preference = koinInject()
    val widget by prefs.widget.collectAsState()

    val backgroundColor = rememberSaveable {
        mutableStateOf(widget.backgroundColor)
    }

    DialogWrapper(onDismiss = onDismiss) {
        Column() {
            DialogPreferenceTitle(text = stringResource(id = R.string.widget_background_color_title))
            DialogSelector(
                text = WidgetBackground.WHITE.desc,
                checked = (backgroundColor.value == WidgetBackground.WHITE),
                onCheckedChange = { backgroundColor.value = WidgetBackground.WHITE })
            DialogSelector(
                text = WidgetBackground.GRAY.desc,
                checked = (backgroundColor.value == WidgetBackground.GRAY),
                onCheckedChange = { backgroundColor.value = WidgetBackground.GRAY })
            DialogSelector(
                text = WidgetBackground.BLACK.desc,
                checked = (backgroundColor.value == WidgetBackground.BLACK),
                onCheckedChange = { backgroundColor.value = WidgetBackground.BLACK })
            DialogSelector(
                text = WidgetBackground.NONE.desc,
                checked = (backgroundColor.value == WidgetBackground.NONE),
                onCheckedChange = { backgroundColor.value = WidgetBackground.NONE })
            DialogAcceptCancelButtons(accept = {
                onAccept(backgroundColor.value)
            }, cancel = { onCancel() })
        }
    }
}