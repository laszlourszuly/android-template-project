package com.echsylon.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: Observer<in T>): Observer<T> {
    val liveData = this
    val wrapper = object : Observer<T> {
        override fun onChanged(t: T) {
            observer.onChanged(t)
            liveData.removeObserver(this)
        }
    }
    liveData.observe(owner, wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeUntil(
    owner: LifecycleOwner,
    predicate: (T) -> Boolean,
    observer: Observer<in T>
): Observer<T> {
    val liveData = this
    val wrapper = object : Observer<T> {
        override fun onChanged(t: T) {
            observer.onChanged(t)
            if (predicate.invoke(t)) {
                liveData.removeObserver(this)
            }
        }
    }
    liveData.observe(owner, wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeFuture(owner: LifecycleOwner, observer: Observer<in T>): Observer<T> {
    val wrapper = object : Observer<T> {
        private var flag = AtomicBoolean(value == null)
        override fun onChanged(t: T) {
            if (flag.getAndSet(true)) {
                observer.onChanged(t)
            }
        }
    }
    this.observe(owner, wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeFutureOnce(owner: LifecycleOwner, observer: Observer<in T>): Observer<T> {
    val liveData = this
    val wrapper = object : Observer<T> {
        private var flag = AtomicBoolean(value == null)
        override fun onChanged(t: T) {
            if (flag.getAndSet(true)) {
                observer.onChanged(t)
                liveData.removeObserver(this)
            }
        }
    }
    liveData.observe(owner, wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeFutureUntil(
    owner: LifecycleOwner,
    predicate: (T) -> Boolean,
    observer: Observer<in T>
): Observer<T> {
    val liveData = this
    val wrapper = object : Observer<T> {
        private var flag = AtomicBoolean(value == null)
        override fun onChanged(t: T) {
            if (flag.getAndSet(true)) {
                observer.onChanged(t)
                if (predicate.invoke(t)) {
                    liveData.removeObserver(this)
                }
            }
        }
    }
    liveData.observe(owner, wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeForeverOnce(observer: Observer<in T>): Observer<T> {
    val liveData = this
    val wrapper = object : Observer<T> {
        override fun onChanged(t: T) {
            observer.onChanged(t)
            liveData.removeObserver(this)
        }
    }
    liveData.observeForever(wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeForeverUntil(predicate: (T) -> Boolean, observer: Observer<in T>): Observer<T> {
    val liveData = this
    val wrapper = object : Observer<T> {
        override fun onChanged(t: T) {
            observer.onChanged(t)
            if (predicate.invoke(t)) {
                liveData.removeObserver(this)
            }
        }
    }
    liveData.observeForever(wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeForeverFuture(observer: Observer<in T>): Observer<T> {
    val wrapper = object : Observer<T> {
        private var flag = AtomicBoolean(value == null)
        override fun onChanged(t: T) {
            if (flag.getAndSet(true)) {
                observer.onChanged(t)
            }
        }
    }
    this.observeForever(wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeForeverFutureOnce(observer: Observer<in T>): Observer<T> {
    val liveData = this
    val wrapper = object : Observer<T> {
        private var flag = AtomicBoolean(value == null)
        override fun onChanged(t: T) {
            if (flag.getAndSet(true)) {
                observer.onChanged(t)
                liveData.removeObserver(this)
            }
        }
    }
    liveData.observeForever(wrapper)
    return wrapper
}

fun <T> LiveData<T>.observeForeverFutureUntil(predicate: (T) -> Boolean, observer: Observer<in T>): Observer<T> {
    val liveData = this
    val wrapper = object : Observer<T> {
        private var flag = AtomicBoolean(value == null)
        override fun onChanged(t: T) {
            if (flag.getAndSet(true)) {
                observer.onChanged(t)
                if (predicate.invoke(t)) {
                    liveData.removeObserver(this)
                }
            }
        }
    }
    liveData.observeForever(wrapper)
    return wrapper
}
