package com.echsylon.core.extension

import android.content.res.Resources
import android.util.TypedValue

fun Int.asDp(): Float {
    val displayMetrics = Resources.getSystem().displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics)
}
