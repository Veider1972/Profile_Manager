package ru.veider.profilemanager.utils

import kotlin.math.abs

fun Float.isInt() = abs(this - this.toInt()) < 0.01