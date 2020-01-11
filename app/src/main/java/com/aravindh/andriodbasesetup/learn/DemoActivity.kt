package com.aravindh.andriodbasesetup.learn

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.databinding.ActivityDemoBinding


val MYBROADCASTRECEIVER = "com.CUSTOM_INTENT"
const val BROADCAST_MESSAGE = "broadcast_message"

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding
    private lateinit var intentService: Intent
    private lateinit var receiver: MyBroadCastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo)
        receiver = MyBroadCastReceiver()


        intentService = Intent(this, MyService::class.java)

        binding.buttonStartService.setOnClickListener {
            startService(intentService)
        }

        binding.buttonStopService.setOnClickListener {
            stopService(intentService)
        }

        binding.buttonBroadCastReceiver.setOnClickListener {
            val message = binding.editTextBroadCastMessage.text.toString().trim()

            if (message.isNotEmpty()) {
                val intent = Intent()
                intent.action = MYBROADCASTRECEIVER
                intent.putExtra(BROADCAST_MESSAGE, message)
                sendBroadcast(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(receiver, IntentFilter(MYBROADCASTRECEIVER))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
