package com.zhan.hy.mvvm.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */

inline fun <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    this.startActivity(intent)
}


fun Context.log(message: String) {
    Log.d(this.javaClass.name, message)
}

fun String.showLog() {
    Log.d(this.javaClass.name, "<------------------------------")
    Log.d(this.javaClass.name, "${this.javaClass.name}:  $this")
    Log.d(this.javaClass.name, "------------------------------->")
}