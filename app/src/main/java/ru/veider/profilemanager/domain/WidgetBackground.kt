package ru.veider.profilemanager.domain

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import ru.veider.profilemanager.R

@Serializable
sealed class WidgetBackground(@ColorRes private val startRes: Int?, @ColorRes private val endRes: Int?, val startColor: @Contextual Color?, val endColor: @Contextual Color?, val transparency: Float, @StringRes val desc: Int) {
    @Serializable data object White: WidgetBackground(R.color.white_start, R.color.white_end, null, null, 1f,R.string.white_color)
    @Serializable data object Gray: WidgetBackground(R.color.gray_start,R.color.gray_end, null, null, 1f,R.string.gray_color)
    @Serializable data object Black: WidgetBackground(R.color.black_start,R.color.black_end, null, null, 1f, R.string.black_color)
    @Serializable data object Transparent: WidgetBackground(R.color.color_transparent,R.color.color_transparent, null, null, 0f, R.string.transparent_color)
    @Serializable data class Custom(val start: @Contextual Color, val end: @Contextual Color, val alpha: Float): WidgetBackground( null, null, start,end, alpha, R.string.custom_color)
    fun startColor(context: Context) = this.startRes?.let { Color(context.getColor(it)) } ?: this.startColor ?: Color(context.getColor(R.color.white_start))
    fun endColor(context: Context) = this.endRes?.let { Color(context.getColor(it)) } ?: this.endColor ?: Color(context.getColor(R.color.white_end))
    fun gradient(context: Context) = listOf(startColor(context), endColor(context))
}