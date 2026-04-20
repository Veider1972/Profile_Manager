package ru.veider.profilemanager.ui.assets.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.assets.buttons.DialogButtons
import ru.veider.profilemanager.ui.assets.elements.DialogTitle
import ru.veider.profilemanager.ui.theme.doublePadding


@Composable
fun TwoButtonDialog(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    acceptButtonText: String,
    onAccept: () -> Unit,
    cancelButtonText: String,
    onCancel: () -> Unit
) {
    DialogWrapper() {
        Column(
            modifier = modifier.width(IntrinsicSize.Max)
        ) {
            DialogTitle(title = title)
            Text(
                modifier = Modifier.padding(doublePadding),
                text = message
            )
            DialogButtons(
                acceptButtonText = acceptButtonText,
                acceptEnabled = true,
                onAccept = onAccept,
                cancelButtonText = cancelButtonText,
                onCancel = onCancel,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Preview
@Composable
private fun TwoButtonDialogShow() {
	TwoButtonDialog(
		title = stringResource(R.string.error_title),
		message = stringResource(ru.veider.profilemanager.R.string.text_seconds),
		acceptButtonText = stringResource(ru.veider.profilemanager.R.string.accept),
		onAccept = {},
		cancelButtonText = stringResource(ru.veider.profilemanager.R.string.cancel),
		onCancel = {})
}