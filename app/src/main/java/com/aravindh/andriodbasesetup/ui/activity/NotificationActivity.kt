package com.aravindh.andriodbasesetup.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.utils.Logger

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val intent = intent.extras
        intent?.let {
            Logger.d("notification url " + it.getString("url"))
            Logger.d("notification type " + it.getString("type"))
        }
    }
}
