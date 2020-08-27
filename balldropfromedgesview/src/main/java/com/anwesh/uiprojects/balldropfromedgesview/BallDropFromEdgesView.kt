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
