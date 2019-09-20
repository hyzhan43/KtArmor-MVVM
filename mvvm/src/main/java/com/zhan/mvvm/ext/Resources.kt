package com.zhan.mvvm.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 *  @author: HyJame
 *  @date:   2019-09-20
 *  @desc:   resources 基本封装
 */

// R.color.xxx  -> @ColorInt
fun Context.getColorRef(@ColorRes res: Int): Int {
    return ContextCompat.getColor(this, res)
}

// R.drawable.xxx -> Drawable
fun Context.getDrawableRef(@DrawableRes res: Int): Drawable? {
    return ContextCompat.getDrawable(this, res)
}

fun View.getColorRef(@ColorRes res: Int): Int {
    return ContextCompat.getColor(this.context, res)
}

fun View.getDrawableRef(@DrawableRes res: Int): Drawable? {
    return ContextCompat.getDrawable(this.context, res)
}