package com.spectralink.battery

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    private val PREFS_FILENAME = "com.spectralink.battery.prefs"
    private val BATTERY_LEVEL = "currentLevel"
    private val BATTERY_STATUS = "status"
    private val LEVEL1 = "level1"
    private val VIBRATE1 = "vibrate1"
    private val LEVEL2 = "level2"
    private val VIBRATE2 = "vibrate2"
    private val ENABLED = "enabled"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var level: Int
        get() = prefs.getInt(BATTERY_LEVEL, 100)
        set(value) = prefs.edit().putInt(BATTERY_LEVEL, value).apply()

    var status: String
        get() = prefs.getString(BATTERY_STATUS, "")
        set(status) = prefs.edit().putString(BATTERY_STATUS, status).apply()

    var enabled: Boolean
        get() = prefs.getBoolean(ENABLED, false)
        set(enabled) = prefs.edit().putBoolean(VIBRATE1, enabled).apply()

    var level1: Int
        get() = prefs.getInt(LEVEL1, 100)
        set(level1) = prefs.edit().putInt(LEVEL1, level1).apply()

    var vibrateEnabled1: Boolean
        get() = prefs.getBoolean(VIBRATE1, false)
        set(vibrateEnabled1) = prefs.edit().putBoolean(VIBRATE1, vibrateEnabled1).apply()

    var level2: Int
        get() = prefs.getInt(LEVEL2, 100)
        set(level2) = prefs.edit().putInt(LEVEL2, level2).apply()

    var vibrateEnabled2: Boolean
        get() = prefs.getBoolean(VIBRATE2, false)
        set(vibrateEnabled2) = prefs.edit().putBoolean(VIBRATE2, vibrateEnabled2).apply()




}