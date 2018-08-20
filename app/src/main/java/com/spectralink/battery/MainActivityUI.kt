package com.spectralink.battery

import android.view.Gravity
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivityUI : AnkoComponent<MainActivity>, AnkoLogger {
    val notificationTypes = listOf("Toast", "Sound")
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        info { "initView method" }
        verticalLayout {
            val lowBatteryAlertLevel1 = editText({
                hint = "battery Level1"
            })
            lowBatteryAlertLevel1.setText(prefs.level1)

            radioGroup() {
                orientation = LinearLayout.HORIZONTAL
                radioButton {
                    text = "Toast"
                }
                radioButton {
                    text = "Sound"
                }


                gravity = Gravity.CENTER
            }


            val lowBatteryAlertLevel2 = editText({
                hint = "battery Level2"
            })
            lowBatteryAlertLevel2.setText(prefs.level2)
            radioGroup() {
                orientation = LinearLayout.HORIZONTAL
                radioButton {
                    text = "Toast"
                }
                radioButton {
                    text = "Sound"
                }


                gravity = Gravity.CENTER
            }

            textView {
                text = "Battery Level is ${prefs.level}"
            }
        }
    }
}