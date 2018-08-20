package com.spectralink.battery

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange


class MainActivity() : AppCompatActivity(), AnkoLogger {
    var batteryBroadcastReceiver = BatteryBroadcastReceiver();

    val CHANNEL_ID = "com.spectralink.battery"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadViewFromSharedPrefs()
        createNotificationChannel()

        initOnChangeListeners()
    }

    private fun initOnChangeListeners() {
        enableNotifications.setOnCheckedChangeListener { _, b -> prefs.enabled = b }
        level1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                prefs.level1 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        checkBoxVibrate1.setOnCheckedChangeListener { buttonView, isChecked ->
            prefs.vibrateEnabled1 = isChecked
        }

        level2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                prefs.level2 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        checkBoxVibrate2.setOnCheckedChangeListener { buttonView, isChecked ->
            prefs.vibrateEnabled2 = isChecked
        }
    }

    fun loadViewFromSharedPrefs() {
        checkBoxVibrate1.setChecked(prefs.vibrateEnabled1)
        checkBoxVibrate2.setChecked(prefs.vibrateEnabled2)

        level1.progress = prefs.level1
        level2.progress = prefs.level2

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
