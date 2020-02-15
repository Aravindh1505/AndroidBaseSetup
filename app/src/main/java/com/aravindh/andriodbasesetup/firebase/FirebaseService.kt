package com.aravindh.andriodbasesetup.firebase

import com.aravindh.andriodbasesetup.utils.Logger
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 *Created by Aravindh S on 11-02-2020.
 */

class FirebaseService : FirebaseMessagingService() {



    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        Logger.d("newToken :$newToken")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        for (data in remoteMessage.data) {
            Logger.d("remoteMessage :${data.value}")
        }
    }

}