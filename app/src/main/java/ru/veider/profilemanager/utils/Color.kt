package ru.veider.profilemanager.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import ru.veider.profilemanager.R
import ru.veider.profilemanager.app
import ru.veider.profilemanager.domain.CMYK
import ru.veider.profilemanager.ui.theme.colorBlack
import ru.veider.profilemanager.ui.theme.colorWhite

fun Color.toCmyk(): CMYK {
    val r = red
    val g = green
    val b = blue

    // Вычисляем черный как минимум из дополнительных цветов
    val k = 1f - maxOf(r, g, b)

    // Защита от деления на ноль
    if (k >= 0.999f) {
        return CMYK(0f, 0f, 0f, 1f, alpha)
    }

    // Вычисляем остальные компоненты
    val c = (1f - r - k) / (1f - k)
    val m = (1f - g - k) / (1f - k)
    val y = (1f - b - k) / (1f - k)

    return CMYK(
        cyan = c.coerceIn(0f, 1f),
        magenta = m.coerceIn(0f, 1f),
        yellow = y.coerceIn(0f, 1f),
        black = k.coerceIn(0f, 1f),
        alpha = alpha
    )
}

fun CMYK.toColor(): Color {
    // Конвертируем CMYK в RGB
    val r = (1f - cyan) * (1f - black)
    val g = (1f - magenta) * (1f - black)
    val b = (1f - yellow) * (1f - black)

    return Color(r, g, b, alpha)
}

fun Color.pressed(): Color{
    val cmyk = this.toCmyk()
    return cmyk.copy(black = cmyk.black * 0.8f).toColor()
}

fun Color.contrast(): Color{
    val cmyk = this.toCmyk().copy(alpha = 1f)
    return cmyk.copy(
        black = if (cmyk.black >= 0.5f) cmyk.black - 0.2f else cmyk.black + 0.2f
    ).toColor()
}
fun Color.doubleContrast(): Color{
    val cmyk = this.toCmyk().copy(alpha = 1f)
    return cmyk.copy(
        black = if (cmyk.black >= 0.5f) cmyk.black - 0.5f else cmyk.black + 0.5f
    ).toColor()
}
fun Color.wheelColor(context: Context): Color{
    val cmyk = this.toCmyk().copy(alpha = 1f)
    return if (cmyk.black < 0.5f) Color(context.getColor(R.color.black)) else Color(context.getColor(R.color.white))
}