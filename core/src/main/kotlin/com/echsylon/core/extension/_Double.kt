package com.echsylon.core.extension

import android.content.res.Resources
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP
import kotlin.math.roundToInt

fun Double.roundToIntBetween(range: IntRange): Int = when {
    isNaN() -> throw IllegalArgumentException("Cannot round NaN value.")
    this > range.last -> range.last
    this < range.first -> range.first
    else -> this.roundToInt()
}

fun Double.asDp(): Float {
    val displayMetrics = Resources.getSystem().displayMetrics
    return TypedValue.applyDimension(COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics)
}
