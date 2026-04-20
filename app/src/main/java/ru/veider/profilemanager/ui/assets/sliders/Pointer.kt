package ru.veider.profilemanager.ui.assets.sliders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.ui.theme.colorGray
import ru.veider.profilemanager.ui.theme.colorInactive
import ru.veider.profilemanager.ui.theme.colorPrimary
import ru.veider.profilemanager.ui.theme.colorPrimaryDark

@Composable
fun Pointer(
    diameter: Dp,
    enabled: Boolean = true
) {

    val density = LocalDensity.current
    val radius = remember { density.run { diameter.toPx() }/ 2f}
    val startColor = if (enabled) colorPrimary else colorInactive
    val endColor = if (enabled) colorPrimaryDark else colorGray

    val brush = remember(diameter) {
        Brush.radialGradient(
            colors = listOf(startColor, endColor),
            center = Offset(radius * 0.8f,radius * 0.8f)
        )
    }

    Box(
        modifier = Modifier
            .size(diameter)
            .clip(CircleShape)
            .background(
                brush = brush,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {}
}

@Preview
@Composable
private fun Preview() {
    Pointer(
        diameter = 24.dp
    )
}