package com.spectralink.slnkbattlife

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

val prefs: Prefs by lazy {
    BatteryApp.prefs!!
}

class BatteryApp : Application(), AnkoLogger {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        info { "called onCreate of BatteryApp" }
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}