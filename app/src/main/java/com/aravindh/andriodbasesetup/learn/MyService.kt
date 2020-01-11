package com.aravindh.andriodbasesetup.learn

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.utils.Logger
import java.util.*


/**
 *Created by Aravindh S on 05-01-2020.
 */

class MyService : Service() {

    var mediaPlayer: MediaPlayer? = null

    var timer: Timer? = null

    /** indicates how to behave if the service is killed  */
    var mStartMode = 0

    /** interface for clients that bind  */
    var mBinder: IBinder? = null

    /** indicates whether onRebind should be used  */
    var mAllowRebind = false

    /** Called when the service is being created.  */
    override fun onCreate() {
        Logger.d("Service Created.......")
        timer = Timer()

        mediaPlayer = MediaPlayer.create(this, R.raw.sample)
        mediaPlayer?.isLooping = false

    }

    /** The service is starting, due to a call to startService()  */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        /*timer?.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    Logger.d("Timer running.....")
                }

            }, 0, 2000
        )*/
        mediaPlayer?.start()

        //stopSelf()
        return mStartMode
    }

    /** A client is binding to the service with bindService()  */
    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    /** Called when all clients have unbound with unbindService()  */
    override fun onUnbind(intent: Intent?): Boolean {
        return mAllowRebind
    }

    /** Called when a client is binding to the service with bindService() */
    override fun onRebind(intent: Intent?) {}

    /** Called when The service is no longer used and is being destroyed  */
    override fun onDestroy() {
        Logger.d("Service Destroyed......")
//        timer?.cancel()
        mediaPlayer?.stop()
    }


}