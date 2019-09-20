package com.zhan.mvvm.ext

import android.util.Log
import com.zhan.mvvm.constant.Const

/**
 *  @author: HyJame
 *  @date:   2019-09-20
 *  @desc:   Log 基本封装
 */
fun logv(message: String, tag: String = Const.KT_ARMOR) = Log.v(tag, message)

fun logd(message: String, tag: String = Const.KT_ARMOR) = Log.d(tag, message)

fun logi(message: String, tag: String = Const.KT_ARMOR) = Log.i(tag, message)

fun logw(message: String, tag: String = Const.KT_ARMOR) = Log.w(tag, message)

fun loge(message: String, tag: String = Const.KT_ARMOR) = Log.e(tag, message)

fun String.showLog() {
    Log.d(Const.KT_ARMOR, "<<<<<<--------------------------")
    Log.d(Const.KT_ARMOR, "[content]:  $this")
    Log.d(Const.KT_ARMOR, "-------------------------->>>>>>")
}