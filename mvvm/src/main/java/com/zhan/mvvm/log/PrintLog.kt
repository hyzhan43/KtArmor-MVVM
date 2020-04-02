package com.zhan.mvvm.log

import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.KtArmor

/**
 *  author: HyJame
 *  date:   2020/4/2
 *  desc:   KtArmor log日志
 */
object PrintLog {

    fun log(msg: String) {
        if (KtArmor.printLog) {
            logd(msg)
        }
    }
}