package ru.veider.profilemanager.domain

data class CMYK(
    val cyan: Float,
    val magenta: Float,
    val yellow: Float,
    val black: Float,
    val alpha: Float = 1f
)
