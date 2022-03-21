package com.echsylon.core.extension

import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_GO
import android.widget.EditText

fun EditText.onGo(callback: (String?) -> Unit) {
    setOnEditorActionListener { view, actionId, _ ->
        when (actionId) {
            IME_ACTION_GO -> callback.invoke(view.text?.toString()).let { true }
            else -> false
        }
    }
}

fun EditText.onDone(callback: (String?) -> Unit) {
    setOnEditorActionListener { view, actionId, _ ->
        when (actionId) {
            IME_ACTION_DONE -> callback.invoke(view.text?.toString()).let { true }
            else -> false
        }
    }
}
