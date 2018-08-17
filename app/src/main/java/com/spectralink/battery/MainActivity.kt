package com.spectralink.battery

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.setContentView


class MainActivity : AppCompatActivity(), AnkoLogger {
    var batteryBroadcastReceiver = BatteryBroadcastReceiver();

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        MainActivityUI().setContentView(this)
    }

    override fun onStart() {
        val batteryLevelFilter = IntentFilter()
        batteryLevelFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        batteryLevelFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        batteryLevelFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(this.batteryBroadcastReceiver, batteryLevelFilter)
        super.onStart()
        info { "onStart called" }
    }

    override fun onStop() {
        unregisterReceiver(this.batteryBroadcastReceiver)
        super.onStop()
        info { "onStop called" }
    }
}
