package com.zhan.mvvm.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View

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

fun Context.log(message: String) {
    Log.d(this.javaClass.name, message)
}

fun String.showLog() {
    Log.d(this.javaClass.name, "<------------------------------")
    Log.d(this.javaClass.name, "[${this.javaClass.name}]:  $this")
    Log.d(this.javaClass.name, "------------------------------->")
}