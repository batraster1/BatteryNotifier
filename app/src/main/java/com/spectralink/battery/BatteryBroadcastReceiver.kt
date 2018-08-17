package com.spectralink.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class BatteryBroadcastReceiver : BroadcastReceiver(), AnkoLogger {
    override fun onReceive(context: Context, intent: Intent) {
        var level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        var scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if (level != -1 && scale != -1) prefs.level =  ((level /  scale) * 100)

        val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)

        var status = ""
        when (plugged) {
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> status = "Plugged in Wireless"
            BatteryManager.BATTERY_PLUGGED_USB -> status = "Plugged in USB"
            BatteryManager.BATTERY_PLUGGED_AC -> status = "Plugged in AC"
            else -> status = "Not plugged in"
        }
        prefs.status = status

        doNotification(prefs.level, status)

    }

    private fun doNotification(level: Int, status: String) {
        info { "doing stub notification" }
    }

}