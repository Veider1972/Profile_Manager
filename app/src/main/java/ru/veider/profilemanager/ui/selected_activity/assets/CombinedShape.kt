package ru.veider.profilemanager.ui.selected_activity.assets

import android.util.Log
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import ru.veider.profilemanager.domain.selector.Circle
import ru.veider.profilemanager.utils.createSmoothPath
import kotlin.math.*

class CombinedShape(
    val num: Int,
    val diameter: Float,
    val fullSize: Size,
    val rotation: Float
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        var path = Path()
        val radius = diameter / 2
        val smallRadius = radius * 2 / 3
        val center = Offset(fullSize.center.x, fullSize.center.y)
        val circle1 = Circle(
            center = center,
            radius = smallRadius
        )
        if (num > 0) {
            val delta = 2 * PI / num

            repeat(num) {
                val x = diameter * sin(it * delta + rotation) + center.x
                val y = -diameter * cos(it * delta + rotation) + center.x
                val circle2 = Circle(center = Offset(x.toFloat(), y.toFloat()), radius)
                Log.d("Shape", "rotation=$rotation")
                val newPath = createSmoothPath(circle1, circle2, smallRadius)
                Log.d("Shape", "newPath получен")
                try {
                    path = Path.combine(
                        PathOperation.Union,
                        path,
                        newPath
                    )
                } catch(t: Throwable){
                    Log.d("Shape", "Ошибка ${t.message}")
                }

            }
        }
        return Outline.Generic(path)
    }
}

