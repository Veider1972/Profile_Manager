package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.preference.WidgetBackgroundTransparency
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.preference_activity.state.WidgetState
import ru.veider.profilemanager.ui.theme.dialogText

@Composable
fun TransparencySetDialog(
    widget: WidgetState,
    onDismiss: () -> Unit,
    onAccept: (WidgetBackgroundTransparency) -> Unit,
    onCancel: () -> Unit
) {
    var transparency by rememberSaveable {
        mutableIntStateOf(widget.backgroundTransparency.value)
    }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.widget_transparency_title))
        Text(
            text = WidgetBackgroundTransparency(transparency).desc,
            textAlign = TextAlign.Center,
            style = dialogText,
            modifier = Modifier.fillMaxWidth()
        )
        Slider(
            value = 100 - transparency.toFloat(),
            valueRange = 0f..100f,
            onValueChange = { transparency = 100 - it.toInt() },
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.single_padding))
        )
        DialogAcceptCancelButtons(accept = {
            onAccept(WidgetBackgroundTransparency(transparency))
        }, cancel = { onCancel() })
    }
}