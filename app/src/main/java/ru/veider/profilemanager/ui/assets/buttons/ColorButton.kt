package ru.veider.profilemanager.ui.assets.buttons

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.*
import ru.veider.profilemanager.utils.*

@Composable
fun ColorButton(
    text: String,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val style = textStyle_12_400
    val padding = halfPadding
    val size = remember {
        density.run {
            val size = textMeasurer.measure(text, style).size
            DpSize(size.width.toDp() + padding * 2, size.height.toDp() + padding * 2)
        }
    }

    Surface(
        color =
            if (pressed) {
                color.pressed()
            } else {
                color
            },
        border = BorderStroke(1.dp, if (selected) color.doubleContrast() else colorTransparent),
        shape = RoundedCornerShape(6.dp),
        shadowElevation = if (pressed) dimensionResource(id = R.dimen.half_elevation) else dimensionResource(id = R.dimen.single_padding),
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                role = Role.Button,
                onClick = onClick
            )
    ) {
        Box(
            modifier = Modifier.size(size),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.transparent_background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    style = style,
                    color = color.doubleContrast()
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ColorButton(
        text = "Старт",
        color = Color.Blue,
        selected = true,
        onClick = {}
    )

}