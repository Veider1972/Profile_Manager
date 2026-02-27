package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import org.koin.dsl.koinApplication
import ru.veider.profilemanager.MainApp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.preference.WidgetBackground
import ru.veider.profilemanager.domain.preference.WidgetColor
import ru.veider.profilemanager.domain.preference.WidgetSymbol
import ru.veider.profilemanager.ui.theme.colorGray
import ru.veider.profilemanager.ui.theme.colorGreen
import ru.veider.profilemanager.utils.gradientCircleBitmap

@Composable
fun DrawMenu(size: Dp, widgetBackground:WidgetBackground, ringColor:WidgetColor, onClick: () -> Unit) {

    val context = LocalContext.current
    val density = LocalDensity.current

    val startColor by rememberUpdatedState(widgetBackground.gradient(context).first())
    val finishColor by rememberUpdatedState(widgetBackground.gradient(context).last())

    val bitmapSize by remember(size){ mutableStateOf(density.run { size.toPx() }.toInt()) }
    val strokeWidthDp = remember { 3.dp }
    val strokeWidth = remember { density.run { strokeWidthDp.toPx() }}

    val bitmap by remember(widgetBackground){
        mutableStateOf(
            gradientCircleBitmap(
                sizePx = bitmapSize,
                startColor = startColor ,
                endColor = finishColor,
                angle = 45f
            ).asImageBitmap()
        )
    }

    Box(modifier = Modifier
        .clip(CircleShape)
        .size(size)
        .aspectRatio(1f)
        .clickable{
            onClick()
        }) {
        Image(
            bitmap = bitmap,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(70.dp)
        )
        val color = ringColor.color
        Canvas(modifier = Modifier
            .size(size)
            .align(Alignment.Center), onDraw = {

            drawCircle(color = color, radius = (size / 2).toPx() - strokeWidth, center = Offset(bitmapSize / 2f,bitmapSize / 2f), style = Stroke(strokeWidth))
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
        widgetBackground = WidgetBackground.WHITE,
        ringColor = WidgetColor.GREEN,
        onClick = {}
    )

}