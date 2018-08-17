package com.spectralink.battery

import android.view.Gravity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        val notificationTypes = listOf("Toast", "Sound")
        verticalLayout {

            gravity = Gravity.CENTER
            padding = dip(20)

            val lowBatteryAlertLevel1 = editText({
                hint = "battery Level1"
            })

            button("Notification type", {
                onClick {
                    selector("Notification when battery goes below ${lowBatteryAlertLevel1.text}", notificationTypes, { dialogInterface, i ->
                        toast("Phone will ${notificationTypes[i]} when battery level is below ${lowBatteryAlertLevel1.text}")
                        prefs.level1 = lowBatteryAlertLevel1.text.toString()
                        prefs.notificationLevel1 = notificationTypes[i]
                    })
                }
            })

            val lowBatteryAlertLevel2 = editText({
                hint = "battery Level2"
            })

            button("Notification type", {
                onClick {
                    selector("Notification when battery goes below ${lowBatteryAlertLevel2.text}", notificationTypes, { dialogInterface, i ->
                        toast("Phone will ${notificationTypes[i]} when battery level is below ${lowBatteryAlertLevel2.text}")
                        prefs.level2 = lowBatteryAlertLevel1.text.toString()
                        prefs.notificationLevel2 = notificationTypes[i]
                    })
                }
            })

            textView {
                text = "Battery Level is ${prefs.level}"
            }
        }
    }
}