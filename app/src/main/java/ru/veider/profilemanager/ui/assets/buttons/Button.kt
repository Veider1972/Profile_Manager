package ru.veider.profilemanager.ui.assets.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.ui.theme.colorGray
import ru.veider.profilemanager.ui.theme.colorPrimaryDark
import ru.veider.profilemanager.ui.theme.colorTransparent
import ru.veider.profilemanager.ui.theme.singlePadding
import ru.veider.profilemanager.ui.theme.textStyle_16_400

@Composable
fun Button(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.padding(start = singlePadding, bottom = singlePadding, end = singlePadding),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = colorPrimaryDark,
            disabledContainerColor = colorTransparent,
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        contentPadding = PaddingValues(vertical = singlePadding, horizontal = singlePadding)
    ) {
        Text(text = text.uppercase(), style = textStyle_16_400)
    }
}