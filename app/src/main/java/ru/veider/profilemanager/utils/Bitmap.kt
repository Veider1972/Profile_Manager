package ru.veider.profilemanager.utils

import android.content.Context
import android.graphics.*
import androidx.annotation.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.unit.Dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import ru.veider.profilemanager.R
import kotlin.math.roundToInt

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
    val paint = Paint().apply {
        this.shader = shader
        isAntiAlias = true
    }

    canvas.drawCircle(centerX, centerY, radius, paint)

    return bitmap
}



/**
 * Создаёт Bitmap из векторного ресурса с применением указанного цвета
 * @param context Контекст
 * @param vectorResId ID векторного ресурса (R.drawable.your_vector)
 * @param color Цвет для закрашивания (например, Color.RED или ContextCompat.getColor(context, R.color.color_gray))
 * @param width Желаемая ширина в пикселях (если 0, используется оригинальный размер)
 * @param height Желаемая высота в пикселях (если 0, используется оригинальный размер)
 * @return Bitmap с применённым цветом
 */
fun drawVector(
    context: Context,
    @DrawableRes vectorResId: Int,
    @ColorInt color: Int,
    width: Int = 0,
    height: Int = 0
): Bitmap {
    // Получаем исходный drawable
    val originalDrawable = ContextCompat.getDrawable(context, vectorResId)
        ?: throw IllegalArgumentException("Resource with ID $vectorResId is not a valid drawable")

    // Создаём копию через mutate(), чтобы не изменять оригинал
    val drawable = originalDrawable.mutate()

    // Устанавливаем желаемые размеры
    val targetWidth = if (width > 0) width else drawable.intrinsicWidth
    val targetHeight = if (height > 0) height else drawable.intrinsicHeight

    // Устанавливаем границы для drawable
    drawable.setBounds(0, 0, targetWidth, targetHeight)

    // Создаём Bitmap с прозрачным фоном
    val bitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Применяем цвет через ColorFilter
    drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)

    // Рисуем drawable на canvas
    drawable.draw(canvas)

    return bitmap
}

fun Context.pointer(sizeF: Float): Bitmap {
    val size = sizeF.roundToInt()
    val bitmap = createBitmap(size, size)
    val canvas = Canvas(bitmap)
    val shader = RadialGradientShader(
        Offset(sizeF / 2 * 0.8f,sizeF / 2 * 0.8f),
        radius = sizeF / 2,
        listOf(Color(getColor(R.color.color_primary_dark)), Color(getColor(R.color.color_primary)))
    )
    val paint = Paint().apply {
        this.shader = shader
        isAntiAlias = true
    }
    canvas.drawCircle(sizeF / 2, sizeF / 2, sizeF / 2, paint)
    return bitmap
}