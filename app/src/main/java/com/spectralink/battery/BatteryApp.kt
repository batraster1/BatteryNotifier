package com.spectralink.battery

import android.app.Application

val prefs: Prefs by lazy {
    BatteryApp.prefs!!
}


class BatteryApp : Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}