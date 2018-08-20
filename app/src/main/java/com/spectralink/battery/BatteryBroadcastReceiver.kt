package com.spectralink.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.BatteryManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class BatteryBroadcastReceiver : BroadcastReceiver(), AnkoLogger {

    val CHANNEL_ID = "com.spectralink.battery"
    val NOT_PLUGGED_IN = "Not plugged in"
    val NOTIFICATION_ID = 445464

    override fun onReceive(context: Context, intent: Intent) {
        info {"BatteryBroadcastReceiver called"}
        var level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        prefs.level = level

        val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)
        val isPluggedIn = plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB || plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS
        info { "Current battery level: {$level} , is plugged in ${isPluggedIn}" }
        info{"Battery monitoring enabled ${prefs.enabled}"}
        if(!isPluggedIn && prefs.enabled){
            if(level <= prefs.level1){
                doNotification(level, context)
                if(prefs.vibrateEnabled1) vibrate(context)
            }
        }


    }

    private fun vibrate(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)
    }

    private fun doNotification(level: Int, context: Context) {

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.tooltip_frame_dark)
                .setContentTitle("Battery Low Warning")
                .setContentText("Level is ${level} , please charge")
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("Level is ${level} , please charge"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(soundUri)

        val notificationManager = NotificationManagerCompat.from(context)


        notificationManager?.notify(NOTIFICATION_ID, mBuilder.build())
    }

}