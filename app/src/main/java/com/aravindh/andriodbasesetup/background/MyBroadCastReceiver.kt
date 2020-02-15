package com.aravindh.andriodbasesetup.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.aravindh.andriodbasesetup.learn.BROADCAST_MESSAGE


/**
 *Created by Aravindh S on 05-01-2020.
 */

class MyBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(BROADCAST_MESSAGE)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}