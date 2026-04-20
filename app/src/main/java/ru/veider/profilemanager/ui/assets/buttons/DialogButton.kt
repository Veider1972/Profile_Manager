package ru.veider.profilemanager.ui.assets.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DialogButton(
    buttonText: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.End) {
        Button(
            text = buttonText.uppercase(),
            enabled = enabled,
            onClick = onClick
        )
    }
}