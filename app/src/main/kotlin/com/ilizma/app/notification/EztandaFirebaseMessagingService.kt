package com.ilizma.app.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.resources.R
import com.ilizma.player.framework.app.di.CHANNEL_ID
import com.ilizma.player.framework.app.di.CHANNEL_NAME
import kotlin.random.Random

private const val TAG = "MessagingService"

class EztandaFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(
        token: String,
    ) {
        Log.i(TAG, "Firebase new token generated = $token")
        super.onNewToken(token)
    }

    override fun onMessageReceived(
        remoteMessage: RemoteMessage,
    ) {
        super.onMessageReceived(remoteMessage)
        val (title, body) =
            (remoteMessage.notification?.title ?: "") to (remoteMessage.notification?.body ?: "")
        Log.i(TAG, "Firebase new remote message = Title: $title, Body: $body")

        getNotificationBuilder(title, body)
            .also {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    it.setCategory(Notification.CATEGORY_PROMO)
                }
            }
            .let { notify(it) }

    }

    private fun notify(
        builder: NotificationCompat.Builder,
    ) {
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

    private fun getNotificationBuilder(
        title: String,
        body: String,
    ): NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
        .apply {
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setSmallIcon(R.drawable.ic_notification)
            setContentTitle(title)
            setContentText(body)
            NotificationCompat.BigTextStyle()
                .bigText(body)
                .let { setStyle(it) }
            RingtoneManager.TYPE_NOTIFICATION
                .let { RingtoneManager.getDefaultUri(it) }
                .let { setSound(it) }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                NotificationManager.IMPORTANCE_HIGH
            } else {
                NotificationCompat.PRIORITY_HIGH
            }.let { setPriority(it) }
            PendingIntent.getActivity(
                this@EztandaFirebaseMessagingService,
                0,
                Intent(this@EztandaFirebaseMessagingService, MainActivity::class.java),
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                    else -> PendingIntent.FLAG_UPDATE_CURRENT
                },
            ).let { setContentIntent(it) }
            setAutoCancel(true)
        }

}