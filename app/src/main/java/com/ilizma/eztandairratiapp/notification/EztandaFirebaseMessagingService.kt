package com.ilizma.eztandairratiapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ilizma.presentation.media.CHANNEL_ID
import com.ilizma.presentation.media.CHANNEL_NAME
import com.ilizma.presentation.media.NotificationHelper
import kotlin.random.Random

private const val TAG = "MessagingService"

class EztandaFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(
        token: String
    ) {
        Log.i(TAG, "Firebase new token generated = $token")
        super.onNewToken(token)
    }

    override fun onMessageReceived(
        remoteMessage: RemoteMessage
    ) {
        super.onMessageReceived(remoteMessage)
        val (title, body) =
            (remoteMessage.notification?.title ?: "") to (remoteMessage.notification?.body ?: "")
        Log.i(TAG, "Firebase new remote message = Title: $title, Body: $body")

        val builder = NotificationHelper.from(this, title, body)

        NotificationManagerCompat
            .from(this)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                ).let { createNotificationChannel(it) }
                notify(Random.nextInt(), builder.build())
            }
    }

}