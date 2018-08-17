package com.spectralink.battery

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    private val PREFS_FILENAME = "com.spectralink.battery.prefs"
    private val BATTERY_LEVEL = "currentLevel"
    private val BATTERY_STATUS = "status"
    private val LEVEL1 = "level1"
    private val NOTIFICATION_LEVEL1 = "notification_level1"
    private val LEVEL2 = "level2"
    private val NOTIFICATION_LEVEL2 = "notification_level2"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var level: Int
        get() = prefs.getInt(BATTERY_LEVEL, 100)
        set(value) = prefs.edit().putInt(BATTERY_LEVEL, value).apply()

    var status: String
        get() = prefs.getString(BATTERY_STATUS, "")
        set(status) = prefs.edit().putString(BATTERY_STATUS, status).apply()

    var level1: String
        get() = prefs.getString(LEVEL1, "")
        set(level1) = prefs.edit().putString(LEVEL1, level1).apply()

    var notificationLevel1: String
        get() = prefs.getString(NOTIFICATION_LEVEL1, "")
        set(notificationLevel1) = prefs.edit().putString(NOTIFICATION_LEVEL1, notificationLevel1).apply()

    var level2: String
        get() = prefs.getString(LEVEL2, "")
        set(level2) = prefs.edit().putString(LEVEL2, level2).apply()

    var notificationLevel2: String
        get() = prefs.getString(NOTIFICATION_LEVEL2, "")
        set(notificationLevel2) = prefs.edit().putString(NOTIFICATION_LEVEL2, notificationLevel2).apply()


}