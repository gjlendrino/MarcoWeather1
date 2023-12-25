package com.example.marcoweather

import android.view.GestureDetector
import android.view.MotionEvent


//class MyGestureListener(private val onSwipe: MyCallbackClass) : GestureDetector.SimpleOnGestureListener() {
class MyGestureListener(private val prev: (() -> Unit)?, private val next: (() -> Unit)?) : GestureDetector.SimpleOnGestureListener() {
    private val SWIPE_THRESHOLD = 200

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1 != null && e2 != null) {
            val deltaX = e2.x - e1.x
            // Si el deslizamiento es hacia la izquierda, navegar al siguiente fragmento
            if (deltaX < -SWIPE_THRESHOLD && next != null) {
                next.invoke()
                return true
            // Si el deslizamiento es hacia la derecha, navegar al anterior fragmento
            } else if (deltaX > SWIPE_THRESHOLD && prev != null) {
                prev.invoke()
                return true
            }
        }
        return false
    }
}