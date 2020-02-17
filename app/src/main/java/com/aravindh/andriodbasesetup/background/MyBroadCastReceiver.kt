package com.aravindh.andriodbasesetup.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


/**
 *Created by Aravindh S on 05-01-2020.
 */

class MyBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("")
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}