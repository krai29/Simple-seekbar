package com.example.simple_seekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variable to keep track of the initial position of text view based on seek bar progress so that we can move it back to where it started
        // we translate the text view along the y axis
        val initialTextViewTranslationY = textView_progress.translationY

        // Listen to changes of seek bar
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // setting textview to current progress
                textView_progress.text = progress.toString()

                // variable to create animation distance for textview

                // animation steps should be defined such that it's resolution independent
                val translationDistance = (initialTextViewTranslationY + progress *
                        resources.getDimension(R.dimen.text_animation_steps) * -1)
                // -1 because upwards y is negative according to android conventions

                // Now we are animating by passing this distance
                textView_progress.animate().translationY(translationDistance)

                /* 1) from User is a boolean value ..will be true, if the change was done by user and will be false if done by function

                 2) when you call reset, we are setting seekbar progress to 0 which is done programmatically means fromUser is false

                 so one time when we click reset, seekbar is set to 0, which calls the onprogresschanged and then if not fromuser, we are making animation
                 but next time when we call it, already the seekbar is at 0 and hence it won't make any difference

                 */

                if(!fromUser)
                textView_progress.animate().setDuration(500).rotationBy(360f ).translationY(initialTextViewTranslationY)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        reset_button.setOnClickListener { v ->
            seekBar.progress = 0
        }
    }
}
