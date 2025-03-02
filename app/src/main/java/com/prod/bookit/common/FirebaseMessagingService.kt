package com.prod.bookit.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.prod.bookit.R

class MyFirebaseMessagingService(
    private val prefs: SharedPreferences
) : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        prefs.edit().putString("fcm_token", token).apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("INFOG", "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d("INFOG", "Data payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.d("INFOG", "Message Notification Title: ${it.title}")
            showNotification(it.title, it.body)
        }
    }

    private fun showNotification(title: String?, message: String?) {
        val channelId = "default_channel_id"
        val channelName = "Default Channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName,
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(0, notificationBuilder.build())
        }
    }
}
