package ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import ru.veider.profilemanager.domain.WidgetBackground
import ru.veider.profilemanager.ui.theme.colorPrimary
import ru.veider.profilemanager.utils.gradientCircleBitmap

@Composable
fun DrawMenu(size: Dp, widgetBackground: WidgetBackground, color: Color, onClick: () -> Unit) {

    val context = LocalContext.current
    val density = LocalDensity.current

    val bitmapSize by remember(size) { mutableStateOf(density.run { size.toPx() }.toInt()) }
    val strokeWidthDp = remember { 3.dp }
    val strokeWidth = remember { density.run { strokeWidthDp.toPx() } }

    val bitmap by remember(widgetBackground) {
        mutableStateOf(
            gradientCircleBitmap(
                sizePx = bitmapSize,
                startColor = widgetBackground.startColor(context),
                endColor = widgetBackground.endColor(context),
                angle = 45f
            ).asImageBitmap()
        )
    }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(size)
            .aspectRatio(1f)
            .clickable {
                onClick()
            }) {
        Image(
            bitmap = bitmap,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(70.dp)
        )
        val color = color
        Canvas(
            modifier = Modifier
                .size(size)
                .align(Alignment.Center), onDraw = {

                drawCircle(color = color, radius = (size / 2).toPx() - strokeWidth, center = Offset(bitmapSize / 2f, bitmapSize / 2f), style = Stroke(strokeWidth))
            })
        Image(
            imageVector = Icons.Default.Menu,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(size - strokeWidthDp * 5)
        )
    }
}

//@Composable
//fun DrawMenu(size: Dp, widgetBackground:WidgetBackground, ringColor:WidgetColor, symbol:WidgetSymbol, symbolColor:WidgetColor) {
//
//    val context = LocalContext.current
//    val density = LocalDensity.current
//
//    val startColor by rememberUpdatedState(widgetBackground.gradient(context).first())
//    val finishColor by rememberUpdatedState(widgetBackground.gradient(context).last())
//
//    val bitmapSize by remember(size){ mutableStateOf(density.run { size.toPx() }.toInt()) }
//    val strokeWidthDp = remember { 3.dp }
//    val strokeWidth = remember { density.run { strokeWidthDp.toPx() }}
//
//    val bitmap by remember(widgetBackground){
//        mutableStateOf(
//            gradientCircleBitmap(
//                sizePx = bitmapSize,
//                startColor = startColor ,
//                endColor = finishColor,
//                angle = 45f
//            ).asImageBitmap()
//        )
//    }
//
//    Box(modifier = Modifier
//        .size(size)
//        .aspectRatio(1f)) {
//        Image(
//            bitmap = bitmap,
//            contentDescription = "",
//            modifier = Modifier
//                .align(Alignment.Center)
//                .size(70.dp)
//        )
//        val color = ringColor.color
//        Canvas(modifier = Modifier
//            .size(size)
//            .align(Alignment.Center), onDraw = {
//
//            drawCircle(color = color, radius = (size / 2).toPx() - strokeWidth, center = Offset(bitmapSize / 2f,bitmapSize / 2f), style = Stroke(strokeWidth))
//        })
//        Image(painter = painterResource(id = symbol.imageId(symbolColor)),
//            contentDescription = "",
//            modifier = Modifier
//                .align(Alignment.Center)
//                .size(size - strokeWidthDp * 5)
//        )
//    }
//}

@Preview(apiLevel = 35)
@Composable
private fun Preview() {
    DrawMenu(
        size = 50.dp,
        widgetBackground = WidgetBackground.White,
        color = colorPrimary,
        onClick = {}
    )

}