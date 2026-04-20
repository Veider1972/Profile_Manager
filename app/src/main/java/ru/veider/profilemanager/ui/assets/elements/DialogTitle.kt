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
fun DialogTitle(
    title: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Text(
        text = title.uppercase(),
        textAlign = TextAlign.Center,
        color = colorPrimaryDark,
        style = textStyle_18_700,
        modifier = modifier
            .background(color = colorPrimary)
            .padding(doublePadding)
    )
}

@Preview
@Composable
private fun Preview() {
    Box{
        DialogTitle(title = "Дата события", modifier = Modifier.width(500.dp))
    }
}