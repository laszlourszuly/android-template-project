package com.echsylon.core.extension

import android.os.Looper
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.change(v: T?) {
    if (v?.equals(value) == true) return
    set(v)
}

fun <T> MutableLiveData<T>.changeIfValue(k: T?, v: T?) {
    if (value?.equals(v) == true) return
    if (value?.equals(k) != true) return
    set(v)
}

fun <T> MutableLiveData<T>.set(v: T?) {
    if (Looper.myLooper() == Looper.getMainLooper()) value = v
    else postValue(v)
}
