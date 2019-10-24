package com.aravindh.andriodbasesetup.binding

import android.text.TextUtils
import android.widget.EditText


class BindingAdapter() {
    @androidx.databinding.BindingAdapter("passwordValidator")
    fun passwordValidator(editText: EditText, password: String) {
        val minimumLength = 5
        if (TextUtils.isEmpty(password)) {
            editText.error = "Password can not be empty"
            return
        }
        if (editText.text.toString().length < minimumLength) {
            editText.error = "Password must be minimum $minimumLength length"
        } else
            editText.error = null
    }
}