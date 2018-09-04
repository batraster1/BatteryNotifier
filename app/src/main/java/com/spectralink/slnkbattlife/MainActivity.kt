package com.spectralink.slnkbattlife


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.setContentView


class MainActivity() : AppCompatActivity(), AnkoLogger {
    var batteryBroadcastReceiver = BatteryBroadcastReceiver();

    val CHANNEL_ID = "com.spectralink.slnkbattlife"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        MainActivityUI().setContentView(this)
        loadViewFromSharedPrefs()
        createNotificationChannel()
    }

    fun loadViewFromSharedPrefs() {
        checkBoxVibrate1.setChecked(prefs.vibrateEnabled1)
        level1.progress = prefs.level1
        enableNotifications.setChecked(prefs.enabled)
    }

    override fun onStart() {
        val batteryLevelFilter = IntentFilter()
        batteryLevelFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        batteryLevelFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        batteryLevelFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(this.batteryBroadcastReceiver, batteryLevelFilter)
        super.onStart()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, "BatteryInfo", importance)
            channel.description = "Battery notification"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }
}
