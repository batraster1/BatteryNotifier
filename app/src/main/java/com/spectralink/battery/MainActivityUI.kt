package com.spectralink.battery

import android.text.InputType
import android.view.Gravity
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
				bottomMargin = dip(23)
                gravity = Gravity.CENTER_HORIZONTAL
			}
			textView {
				id = R.id.editText

				inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
				text = "Notification level 1 (1-50)"
				gravity = Gravity.CENTER_VERTICAL
			}.lparams {
				marginEnd = dip(8)
				marginStart = dip(8)
				bottomMargin = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
			}
			seekBar {
				id = R.id.level1
				max = 50

			}.lparams(width = dip(300)) {
				marginEnd = dip(8)
				marginStart = dip(8)
				bottomMargin = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
			}
			checkBox {
				id = R.id.checkBoxVibrate1
				text = "Vibrate"
				
			}.lparams {
				bottomMargin = dip(23)
                gravity = Gravity.CENTER_HORIZONTAL
			}

			textView {
				id = R.id.editText2
				inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
				text = "Notification level 2 (1-50)"
				
			}.lparams {
				bottomMargin = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
			}
			seekBar {
				id = R.id.level2
				max = 50
			}.lparams(width = dip(300)) {
				marginEnd = dip(8)
				marginStart = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
			}
			checkBox {
				id = R.id.checkBoxVibrate2
				text = "Vibrate"

			}.lparams {
				bottomMargin = dip(8)
				topMargin = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
                
			}


		}
	}
}
