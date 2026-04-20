package ru.veider.profilemanager.ui.assets.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.ui.theme.*

@Composable
fun SettingsTitle(
    title: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Text(
        text = title,
        color = colorPrimaryDark,
        style = textStyle_16_500,
        modifier = modifier
            .background(color = colorPrimary)
            .padding(halfPadding)
    )
}

@Preview
@Composable
private fun Preview() {
    Box{
        SettingsTitle(title = "Дата события", modifier = Modifier.width(500.dp))
    }
}