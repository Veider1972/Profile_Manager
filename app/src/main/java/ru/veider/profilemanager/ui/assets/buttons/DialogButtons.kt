package ru.veider.profilemanager.ui.assets.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R

@Composable
fun DialogButtons(
    modifier: Modifier = Modifier,
    acceptButtonText: String = stringResource(R.string.accept),
    acceptEnabled: Boolean = true,
    onAccept: () -> Unit,
    cancelButtonText: String = stringResource(R.string.cancel),
    onCancel: () -> Unit
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            text = cancelButtonText.uppercase(),
            onClick = onCancel
        )
        Button(
            text = acceptButtonText.uppercase(),
            enabled = acceptEnabled,
            onClick = onAccept
        )
    }
}

@Preview(widthDp = 1080, heightDp = 1920)
@Composable
private fun Preview() {
    DialogButtons(
        modifier = Modifier.width(1080.dp),
        onAccept = {},
        onCancel = {}
    )

}