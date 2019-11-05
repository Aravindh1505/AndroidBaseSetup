package com.aravindh.andriodbasesetup.utils

import android.content.Context
import android.widget.Toast


object UiUtils {

    fun showMessage(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
