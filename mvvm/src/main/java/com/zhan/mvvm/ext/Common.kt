package com.zhan.mvvm.ext

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
inline fun tryCatch(tryBlock: () -> Unit, catchBlock: (Throwable) -> Unit = {}) {
    try {
        tryBlock()
    } catch (t: Throwable) {
        t.printStackTrace()
        catchBlock(t)
    }
}