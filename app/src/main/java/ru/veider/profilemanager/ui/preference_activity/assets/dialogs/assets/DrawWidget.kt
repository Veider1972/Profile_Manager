package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import android.graphics.drawable.ShapeDrawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetBackground
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetColor
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetSymbol

@Composable
fun DrawWidget(widgetBackground:WidgetBackground, ringColor:WidgetColor, symbol:WidgetSymbol, symbolColor:WidgetColor) {

    val context = LocalContext.current

    Box(modifier = Modifier
        .size(70.dp)
        .padding(end = dimensionResource(id = R.dimen.double_padding))) {
        Image(bitmap = ShapeDrawable.createFromXml(context.resources, context.resources.getXml(widgetBackground.resId)).current.toBitmap(70, 70, null).asImageBitmap(),
              contentDescription = "",
              modifier = Modifier
                  .align(Alignment.Center)
                  .size(70.dp)
        )
        val color = ringColor.color
        Canvas(modifier = Modifier.size(100.dp).align(Alignment.Center), onDraw = {
            drawCircle(color = color, radius = 21.dp.toPx(), center = Offset(25.dp.toPx(),35.dp.toPx()), style = Stroke(3.dp.toPx()))
        })
        Image(painter = painterResource(id = symbol.imageId(symbolColor)),
              contentDescription = "",
              modifier = Modifier
                  .align(Alignment.Center)
                  .size(30.dp)
        )
    }
}