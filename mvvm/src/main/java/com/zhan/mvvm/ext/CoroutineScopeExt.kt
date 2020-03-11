package com.zhan.mvvm.ext

import com.zhan.ktwing.ext.tryCatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
fun CoroutineScope.launchUI(
    block: suspend CoroutineScope.() -> Unit,
    error: ((Throwable) -> Unit)? = null
): Job {

    return this.launch(Dispatchers.Main) {
        tryCatch({
            block()
        }, {
            error?.invoke(it)
        })
    }
}