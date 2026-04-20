package ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.*
import ru.veider.profilemanager.ui.theme.*
import ru.veider.profilemanager.utils.gradientCircleBitmap

@Composable
fun DrawWidget(
    modifier: Modifier = Modifier,
    size: Dp,
    widgetBackground: WidgetBackground,
    color: Color,
    symbol: Symbol,
    onClick: (() -> Unit)? = null
) {

    val context = LocalContext.current
    val density = LocalDensity.current

    val bitmapSize by remember(size) { mutableStateOf(density.run { size.toPx() }.toInt()) }
    val strokeWidthDp = remember { 2.dp }

    val gradient by rememberUpdatedState(
        gradientCircleBitmap(
            sizePx = bitmapSize,
            startColor = widgetBackground.startColor(context),
            endColor = widgetBackground.endColor(context),
            angle = 45f
        ).asImageBitmap()
    )

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    Surface(
        color = colorTransparent,
        modifier = modifier
            .size(size)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        onClick = { onClick() },
                        indication = null,
                        interactionSource = interactionSource
                    )
                } else
                    Modifier
            ),
        shape = CircleShape,
        shadowElevation = if (pressed && onClick != null) 0.dp else singleElevation
    )
    {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                bitmap = gradient,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                contentScale = ContentScale.Fit,
                alpha = widgetBackground.transparency
            )
            val color = color
            Icon(
                painterResource(R.drawable.ring),
                contentDescription = null,
                modifier = Modifier.size(size - strokeWidthDp * 2),
                tint = color
            )
            Icon(
                painter = painterResource(id = symbol.symbol),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(size / 46f * 30f),
                tint = color
            )
        }

    }
}

@Preview
@Composable
private fun Preview() {
    Box() {
        Image(
            painterResource(R.drawable.transparent_background),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .size(LocalMinimumInteractiveComponentSize.current * 1.5f)
        )
        DrawWidget(
            size = 70.dp,
            widgetBackground = WidgetBackground.Custom(Color.Gray, Color.White, 0.5f),
            color = Color.Green,
            symbol = Symbol.Muted,
            onClick = {}
        )
    }

}