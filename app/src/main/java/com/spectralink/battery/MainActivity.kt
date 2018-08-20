package com.spectralink.battery

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainActivity() : AppCompatActivity(), AnkoLogger {
    var batteryBroadcastReceiver = BatteryBroadcastReceiver();

    val CHANNEL_ID = "com.spectralink.battery"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadViewFromSharedPrefs()
        apply.setOnClickListener(applyOnClickListener())
        createNotificationChannel()
    }

    fun applyOnClickListener(): (View) -> Unit {
        return {
            prefs.level1 = level1.text.toString().toInt()
            prefs.vibrateEnabled1 = checkBoxVibrate1.isChecked
            prefs.level2 = level2.text.toString().toInt()
            prefs.vibrateEnabled2 = checkBoxVibrate2.isChecked
            prefs.enabled = enableNotifications.isEnabled
        }
    }

    fun loadViewFromSharedPrefs() {
        info { "from shared prefs" }


        checkBoxVibrate1.setChecked(prefs.vibrateEnabled1)
        checkBoxVibrate2.setChecked(prefs.vibrateEnabled2)

        level1.setText(prefs.level1.toString())
        level2.setText(prefs.level2.toString())

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

    override fun onStop() {
        super.onStop()

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
