package com.zhan.mvvm.ext

import android.content.res.Resources.getSystem
import android.util.TypedValue.*

/**
 *  @author: HyJame
 *  @date:   2019-09-20
 *  @desc:   TODO
 */
val Float.dp2px: Float
    get() = applyDimension(COMPLEX_UNIT_DIP, this, getSystem().displayMetrics)

val Int.dp2px: Int
    get() = applyDimension(COMPLEX_UNIT_DIP, this.toFloat(), getSystem().displayMetrics).toInt()


val Float.sp2px: Float
    get() = applyDimension(COMPLEX_UNIT_SP, this, getSystem().displayMetrics)

val Int.sp2px: Int
    get() = applyDimension(COMPLEX_UNIT_SP, this.toFloat(), getSystem().displayMetrics).toInt()


fun Int.px2dp(): Int = (this / (getSystem().displayMetrics.scaledDensity) + 0.5f).toInt()

fun Int.px2sp(): Int = (this / (getSystem().displayMetrics.scaledDensity) + 0.5f).toInt()
