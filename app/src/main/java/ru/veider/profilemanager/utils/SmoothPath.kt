package ru.veider.profilemanager.utils

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import ru.veider.profilemanager.domain.selector.Circle
import kotlin.math.*

fun createSmoothShape(
    circle1: Circle,
    circle2: Circle,
    virtualRadius: Float
): Shape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = createSmoothPath(circle1, circle2, virtualRadius)
        return Outline.Generic(path)
    }
}

/**
 * Создает Path в форме цифры 8 вокруг двух исходных окружностей
 */
fun createSmoothPath(
    circle1: Circle,
    circle2: Circle,
    virtualRadius: Float
): Path {
    return Path().apply {
        // Вектор между центрами окружностей
        val dx = circle2.center.x - circle1.center.x
        val dy = circle2.center.y - circle1.center.y
        val distance = sqrt(dx * dx + dy * dy)

        // Проверка на корректность данных
        if (distance == 0f) return this

        // Нормализованный вектор направления
        val nx = dx / distance
        val ny = dy / distance

        // Перпендикулярный вектор
        val px = -ny
        val py = nx

        // Расстояние между центрами исходных окружностей
        val d = distance

        // Расчет положения виртуальных окружностей
        val r1 = circle1.radius
        val r2 = circle2.radius
        val vr = virtualRadius

        // Вычисляем x - расстояние от circle1 до проекции центра виртуальной окружности на линию центров
        val x = (d * d + (r1 + vr) * (r1 + vr) - (r2 + vr) * (r2 + vr)) / (2 * d)

        // Вычисляем y - перпендикулярное смещение
        val y = sqrt(max(0f, (r1 + vr) * (r1 + vr) - x * x))

        // Центры виртуальных окружностей (верхняя и нижняя)
        val virtualTop = Offset(
            x = circle1.center.x + nx * x + px * y,
            y = circle1.center.y + ny * x + py * y
        )

        val virtualBottom = Offset(
            x = circle1.center.x + nx * x - px * y,
            y = circle1.center.y + ny * x - py * y
        )

        // Находим точки касания
        fun findTangentPoints(
            sourceCircle: Circle,
            virtualCircle: Circle
        ): Pair<Offset, Offset> {
            // Вектор от исходной окружности к виртуальной
            val dx = virtualCircle.center.x - sourceCircle.center.x
            val dy = virtualCircle.center.y - sourceCircle.center.y
            val dist = sqrt(dx * dx + dy * dy)

            // Точка касания на исходной окружности
            val touchOnSource = Offset(
                x = sourceCircle.center.x + dx * sourceCircle.radius / dist,
                y = sourceCircle.center.y + dy * sourceCircle.radius / dist
            )

            // Точка касания на виртуальной окружности (с противоположной стороны)
            val touchOnVirtual = Offset(
                x = virtualCircle.center.x - dx * virtualCircle.radius / dist,
                y = virtualCircle.center.y - dy * virtualCircle.radius / dist
            )

            return Pair(touchOnSource, touchOnVirtual)
        }

        val (touch1Top, touchTop1) = findTangentPoints(circle1, Circle(virtualTop, vr))
        val (touch2Top, touchTop2) = findTangentPoints(circle2, Circle(virtualTop, vr))
        val (touch1Bottom, touchBottom1) = findTangentPoints(circle1, Circle(virtualBottom, vr))
        val (touch2Bottom, touchBottom2) = findTangentPoints(circle2, Circle(virtualBottom, vr))

        // Функция для расчета углов точки на окружности
        fun getAngle(center: Offset, point: Offset): Float {
            return atan2(point.y - center.y, point.x - center.x)
        }

        // Исправленный порядок построения Path

        // Вариант 1: Обход по часовой стрелке (внешний контур)
        moveTo(touch1Top.x, touch1Top.y)  // Начинаем с верхней точки первой окружности

        // 1. Дуга по верхней части первой окружности до нижней точки
        addArcWithDirection(
            center = circle1.center,
            radius = circle1.radius,
            startAngle = getAngle(circle1.center, touch1Top),
            endAngle = getAngle(circle1.center, touch1Bottom),
            clockwise = false
        )

        // 2. Дуга по нижней виртуальной окружности от первой ко второй
        addArcWithDirection(
            center = virtualBottom,
            radius = vr,
            startAngle = getAngle(virtualBottom, touchBottom1),
            endAngle = getAngle(virtualBottom, touchBottom2),
            clockwise = true
        )

        // 3. Дуга по нижней части второй окружности (от нижней точки к верхней)
        addArcWithDirection(
            center = circle2.center,
            radius = circle2.radius,
            startAngle = getAngle(circle2.center, touch2Bottom),  // Используем touch2Bottom
            endAngle = getAngle(circle2.center, touch2Top),       // ИСПОЛЬЗУЕМ touch2Top!
            clockwise = false
        )

        // 4. Дуга по верхней виртуальной окружности от второй к первой
        addArcWithDirection(
            center = virtualTop,
            radius = vr,
            startAngle = getAngle(virtualTop, touchTop2),  // Используем touchTop2
            endAngle = getAngle(virtualTop, touchTop1),    // Используем touchTop1
            clockwise = true
        )

        close()
    }
}

/**
 * Расширение Path для добавления дуги с указанным направлением
 */
private fun Path.addArcWithDirection(
    center: Offset,
    radius: Float,
    startAngle: Float,
    endAngle: Float,
    clockwise: Boolean
) {
    val rect = Rect(
        left = center.x - radius,
        top = center.y - radius,
        right = center.x + radius,
        bottom = center.y + radius
    )

    val sweepAngle = calculateSweepAngle(startAngle, endAngle, clockwise)

    arcTo(
        rect = rect,
        startAngleDegrees = Math.toDegrees(startAngle.toDouble()).toFloat(),
        sweepAngleDegrees = Math.toDegrees(sweepAngle.toDouble()).toFloat(),
        forceMoveTo = false
    )
}

/**
 * Вычисляет угол развертки дуги
 */
private fun calculateSweepAngle(startAngle: Float, endAngle: Float, clockwise: Boolean): Float {
    return if (clockwise) {
        // По часовой стрелке (отрицательный угол в arcTo)
        if (endAngle > startAngle) {
            -(startAngle + 2 * PI.toFloat() - endAngle)
        } else {
            -(startAngle - endAngle)
        }
    } else {
        // Против часовой стрелки (положительный угол в arcTo)
        if (endAngle > startAngle) {
            endAngle - startAngle
        } else {
            endAngle + 2 * PI.toFloat() - startAngle
        }
    }
}