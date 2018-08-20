package com.spectralink.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.BatteryManager
import android.os.Vibrator
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class BatteryBroadcastReceiver : BroadcastReceiver(), AnkoLogger {

    val CHANNEL_ID = "com.spectralink.battery"

    override fun onReceive(context: Context, intent: Intent) {
        info {"BatteryBroadcastReceiver called"}
        var level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        prefs.level = level

        val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)

        var status = ""
        when (plugged) {
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> status = "Plugged in Wireless"
            BatteryManager.BATTERY_PLUGGED_USB -> status = "Plugged in USB"
            BatteryManager.BATTERY_PLUGGED_AC -> status = "Plugged in AC"
            else -> status = "Not plugged in"
        }
        prefs.status = status
        info { "level ${level} saved level1  ${prefs.level1}"}
        if(level < prefs.level1){
            doNotification(level, status, context)
            if(prefs.vibrateEnabled1) vibrate(context)
        }

    }

    private fun vibrate(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)
    }

    private fun doNotification(level: Int, status: String, context: Context) {

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.navigation_empty_icon)
                .setContentTitle("Battery Level")
                .setContentText("Level ${level} and status ${status}")
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("Level ${level} and status ${status}"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(soundUri)

        val notificationManager = NotificationManagerCompat.from(context)

// notificationId is a unique int for each notification that you must define
        notificationManager?.notify(12345, mBuilder.build())
        info { "stub notification ${level} ${status}" }
    }

}