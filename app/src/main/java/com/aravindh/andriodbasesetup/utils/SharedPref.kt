package com.aravindh.andriodbasesetup.utils

import android.content.Context
import android.content.SharedPreferences


/**
 *Created by Aravindh S on 06-01-2020.
 */

const val IS_LOGGED_IN = "is_logged_in"

class SharedPref {
    private val PREF_MODE = Context.MODE_PRIVATE
    private val PREF_NAME = "MyPref"

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    fun getSharedPref(context: Context): SharedPreferences? {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, PREF_MODE)
        }
        return sharedPreferences
    }

    fun setBoolean(key: String, value: Boolean) {
        editor = sharedPreferences?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun getBoolean(key: String) {
        sharedPreferences?.getBoolean(key, false)
    }

    fun setString(key: String, value: String) {
        editor = sharedPreferences?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun getString(key: String) {
        sharedPreferences?.getString(key, "")
    }
}