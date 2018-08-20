package com.spectralink.battery

import android.text.InputType
import com.spectralink.battery.MainActivity
import com.spectralink.battery.R
import org.jetbrains.anko.*

class MainActivityUI : AnkoComponent<MainActivity>, AnkoLogger {

	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

		verticalLayout {
			toggleButton {
				id = R.id.enableNotifications
				text = "ToggleButton"

			}.lparams {
				marginEnd = dip(8)
				marginStart = dip(8)
				topMargin = dip(8)
			}
			textView {
				id = R.id.editText
				//android:ems = 10 //not support attribute
				inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
				text = "Notification level 1 (1-50)"
			}.lparams {
				marginEnd = dip(8)
				marginStart = dip(8)
				topMargin = dip(15)
			}
			seekBar {
				id = R.id.level1
				max = 50
			}.lparams(width = dip(300)) {
				marginEnd = dip(8)
				marginStart = dip(8)
				topMargin = dip(76)
			}
			checkBox {
				id = R.id.checkBoxVibrate1
				text = "Vibrate"
			}.lparams {
				topMargin = dip(36)
			}

			textView {
				id = R.id.editText2
				inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
				text = "Notification level 2 (1-50)"
			}.lparams {
				bottomMargin = dip(23)
			}
			seekBar {
				id = R.id.level2
				max = 50
			}.lparams(width = dip(300)) {
				marginEnd = dip(8)
				marginStart = dip(8)
				topMargin = dip(148)
			}
			checkBox {
				id = R.id.checkBoxVibrate2
				text = "Vibrate"
			}.lparams {
				bottomMargin = dip(8)
				topMargin = dip(8)
			}


		}
	}
}
