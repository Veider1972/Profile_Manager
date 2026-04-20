package ru.veider.profilemanager.ui.setProfile.assets

import android.util.Log
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import ru.veider.profilemanager.domain.Circle
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
        path.apply {
            addOval(center.toRect(smallRadius * 2))
        }
        if (num > 0) {
            val delta = 2 * PI / num

            repeat(num) {
                val x = diameter * sin(it * delta + rotation) + center.x
                val y = -diameter * cos(it * delta + rotation) + center.x
                val circle2 = Circle(center = Offset(x.toFloat(), y.toFloat()), radius)
                val newPath = createSmoothPath(circle1, circle2, smallRadius)
                try {
                    path = Path.combine(
                        PathOperation.Union,
                        path,
                        newPath
                    )
                } catch(t: Throwable){
                    Log.d("Shape", "Ошибка создания shape ${t.message}")
                }

            }
        }
        return Outline.Generic(path)
    }

    private fun Offset.toRect(diameter: Float) = Rect(Offset(this.x - diameter / 2, this.y - diameter / 2), Size(diameter, diameter))
}

