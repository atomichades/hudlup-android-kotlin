package com.example.hudlup.customComponent

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.hudlup.R


class ProgressButton @JvmOverloads constructor(context: Context, attributeSet: AttributeSet, style: Int =0,
): ConstraintLayout(context,attributeSet,style) {
    var button: Button
    var progressBar: ProgressBar
    var image: ImageView
    init {
        inflate(context, R.layout.progressbutton_layout,this)
        val customAttributesStyle = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressButton, 0, 0)
        button = findViewById<Button>(R.id.btn)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        image = findViewById<ImageView>(R.id.tickImageView)
        try {
            button.text = customAttributesStyle.getString(R.styleable.ProgressButton_buttonText)
        } finally {
            customAttributesStyle.recycle()
        }

    }

    fun animateSignInBtnSuccessful(completeMessage: String, duration:Long){
        button.isEnabled = false // Disable button
        image.visibility = View.INVISIBLE
        progressBar.progress = 0 // reset progress
        progressBar.visibility = View.VISIBLE
        val anim = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        anim.duration = duration // Set the desired animation duration
        button.text = completeMessage
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                progressBar.visibility = View.GONE // Hide the progress bar
                button.text = "" // Clear the button text
                image.visibility = View.VISIBLE // Make the tick visible
            }
        })
        val interpolator = LinearInterpolator()
        anim.interpolator = interpolator
        anim.start()
    }

}