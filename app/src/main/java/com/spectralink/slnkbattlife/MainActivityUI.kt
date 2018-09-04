package com.spectralink.slnkbattlife

import android.text.InputType
import android.view.Gravity
import android.widget.SeekBar
import org.jetbrains.anko.*

class MainActivityUI : AnkoComponent<MainActivity>, AnkoLogger {

	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

		verticalLayout {

			lparams(width  = matchParent, height = matchParent)

			toggleButton {
				id = R.id.enableNotifications
				text = "ToggleButton"
                setOnCheckedChangeListener { buttonView, isChecked ->
                    info {"toggle value is ${isChecked}"}
                    prefs.enabled = isChecked
                }
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
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        prefs.level1 = progress
                        toast("Battery notification level 1 set to ${progress}")
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {

                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {

                    }
                })

			}.lparams(width = dip(300)) {
				marginEnd = dip(8)
				marginStart = dip(8)
				bottomMargin = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
			}
			checkBox {
				id = R.id.checkBoxVibrate1
				text = "Vibrate"
                setOnCheckedChangeListener { buttonView, isChecked ->
                    prefs.vibrateEnabled1 = isChecked
                }
				
			}.lparams {
				bottomMargin = dip(23)
                gravity = Gravity.CENTER_HORIZONTAL
			}

		}
	}
}
