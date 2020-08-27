package com.anwesh.uiprojects.balldropfromedgesview

/**
 * Created by anweshmishra on 28/08/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val colors : Array<Int> = arrayOf(Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW, Color.MAGENTA)
val circles : Int = 6
val parts : Int = circles / 2 + 1
val scGap : Float = 0.02f / parts
val foreColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawBallDropFromEdge(scale : Float, w : Float, h : Float, paint : Paint) {
    val gap : Float = w / (parts)
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    for (j in 0..circles - 1) {
        var k : Int = j
        if (j >= circles / 2) {
            k = circles -1 - j
        }
        save()
        translate(gap * j + gap / 2, gap / 2 + (h - gap) * sf.divideScale(k + 1, parts))
        drawCircle(0f, 0f, gap * 0.5f * sf1, paint)
        restore()
    }
}

fun Canvas.drawBDFENode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    drawBallDropFromEdge(scale, w, h, paint)
}

class BallDropFromEdgesView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}