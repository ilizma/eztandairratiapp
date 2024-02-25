package com.ilizma.app.notification

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.resources.R
import kotlin.random.Random

private const val TAG = "MessagingService"
const val CHANNEL_ID = "com.ilizma.eztanda_channel_id"
const val CHANNEL_NAME = "com.ilizma.eztanda_channel_name"

class EztandaFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(
        remoteMessage: RemoteMessage,
    ) {
        super.onMessageReceived(remoteMessage)
        val (title, body) =
            (remoteMessage.notification?.title ?: "") to (remoteMessage.notification?.body ?: "")

        getNotificationBuilder(title, body)
            .also {
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> it.setCategory(Notification.CATEGORY_PROMO)
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
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH
                    ).let { createNotificationChannel(it) }
                }
                if (ActivityCompat.checkSelfPermission(
                        this@EztandaFirebaseMessagingService,
                        Manifest.permission.POST_NOTIFICATIONS,
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                if (areNotificationsEnabled()) {
                    notify(Random.nextInt(), builder.build())
                }
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
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> NotificationManager.IMPORTANCE_HIGH
                else -> NotificationCompat.PRIORITY_HIGH
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