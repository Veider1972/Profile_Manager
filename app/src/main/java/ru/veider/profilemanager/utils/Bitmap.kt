package ru.veider.profilemanager.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.createBitmap

fun gradientCircleBitmap(
    sizePx: Int,
    startColor: Color,
    endColor: Color,
    angle: Float = 45f
): Bitmap {
    val bitmap = createBitmap(sizePx, sizePx)
    val canvas = Canvas(bitmap)

    // Конвертируем Compose цвета в Android цвета
    val androidStartColor = android.graphics.Color.valueOf(
        startColor.red, startColor.green, startColor.blue, startColor.alpha
    ).toArgb()

    val androidEndColor = android.graphics.Color.valueOf(
        endColor.red, endColor.green, endColor.blue, endColor.alpha
    ).toArgb()

    // Рассчитываем точки для градиента с учётом угла
    val angleRad = Math.toRadians(angle.toDouble())
    val centerX = sizePx / 2f
    val centerY = sizePx / 2f
    val radius = sizePx / 2f

    // Точки начала и конца градиента (по направлению угла)
    val startX = centerX - radius * Math.cos(angleRad).toFloat()
    val startY = centerY - radius * Math.sin(angleRad).toFloat()
    val endX = centerX + radius * Math.cos(angleRad).toFloat()
    val endY = centerY + radius * Math.sin(angleRad).toFloat()

    // Создаём градиент
    val shader = LinearGradient(
        startX, startY, endX, endY,
        intArrayOf(androidStartColor, androidEndColor),
        floatArrayOf(0f, 1f),
        Shader.TileMode.CLAMP
    )

    // Рисуем круг с градиентом
    val paint = android.graphics.Paint().apply {
        this.shader = shader
        isAntiAlias = true
    }

    canvas.drawCircle(centerX, centerY, radius, paint)

    return bitmap
}