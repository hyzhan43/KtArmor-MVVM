package com.zhan.mvvm.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */

inline fun <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    this.startActivity(intent)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

// R.color.xxx  -> @ColorInt
fun Context.getColorRef(@ColorRes res: Int): Int {
    return ContextCompat.getColor(this, res)
}

fun Context.getDrawableRef(@DrawableRes res: Int): Drawable? {
    return ContextCompat.getDrawable(this, res)
}

fun Context.log(message: String) {
    Log.d(this.javaClass.name, message)
}

fun String.showLog() {
    Log.d(this.javaClass.name, "<------------------------------")
    Log.d(this.javaClass.name, "[${this.javaClass.name}]:  $this")
    Log.d(this.javaClass.name, "------------------------------->")
}

inline fun tryCatch(tryBlock: () -> Unit, catchBlock: (Throwable) -> Unit = {}) {
    try {
        tryBlock()
    } catch (t: Throwable) {
        t.toString().showLog()
        catchBlock(t)
    }
}