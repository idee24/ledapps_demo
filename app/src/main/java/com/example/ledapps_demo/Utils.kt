package com.example.ledapps_demo

import android.animation.ValueAnimator
import android.widget.FrameLayout
import android.widget.ImageView

/**
 *@Created by Yerimah on 02/12/2021.
 */

fun zoomOutImage(imageFrame: FrameLayout) {

    val anim = ValueAnimator.ofFloat(1.5f, 1f)
    anim.duration = 1000
    anim.addUpdateListener { animation ->
        imageFrame.scaleX = animation.animatedValue as Float
        imageFrame.scaleY = animation.animatedValue as Float
    }
    anim.start()

    if (height) {
        imageFrame.animate()
            .translationYBy(-500f)
            .setDuration(1000).start()
        height = false
    }
    isFullDisplay = true
}



fun zoomInImage(imageFrame: FrameLayout) {
    if (isFullDisplay) {
        val anim = ValueAnimator.ofFloat(1f, 1.5f)
        anim.duration = 1000
        anim.addUpdateListener { animation ->
            imageFrame.scaleX = animation.animatedValue as Float
            imageFrame.scaleY = animation.animatedValue as Float
        }
        anim.start()
        height = true
        imageFrame.animate()
            .translationYBy(500f)
            .setDuration(1000).start()
        isFullDisplay = false
    }
}

private var isFullDisplay = true
private var height = false

