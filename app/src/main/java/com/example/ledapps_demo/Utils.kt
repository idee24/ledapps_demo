package com.example.ledapps_demo

import android.animation.ValueAnimator
import android.widget.ImageView

/**
 *@Created by Yerimah on 02/12/2021.
 */

fun zoomOutImage(imageView: ImageView) {

    val anim = ValueAnimator.ofFloat(1.5f, 1f)
    anim.duration = 1000
    anim.addUpdateListener { animation ->
        imageView.scaleX = animation.animatedValue as Float
        imageView.scaleY = animation.animatedValue as Float
    }
    anim.start()

    if (height) {
        imageView.animate()
            .translationYBy(-500f)
            .setDuration(1000).start()
        height = false
    }
    isFullDisplay = true
}



fun zoomInImage(imageView: ImageView) {
    if (isFullDisplay) {
        val anim = ValueAnimator.ofFloat(1f, 1.5f)
        anim.duration = 1000
        anim.addUpdateListener { animation ->
            imageView.scaleX = animation.animatedValue as Float
            imageView.scaleY = animation.animatedValue as Float
        }
        anim.start()
        height = true
        imageView.animate()
            .translationYBy(500f)
            .setDuration(1000).start()
        isFullDisplay = false
    }
}

private var isFullDisplay = true
private var height = false

