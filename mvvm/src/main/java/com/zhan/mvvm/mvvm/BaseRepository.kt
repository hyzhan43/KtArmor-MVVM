package com.zhan.mvvm.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
abstract class BaseRepository {

    suspend fun <R> launchIO(block: suspend CoroutineScope.() -> R) = withContext(Dispatchers.IO) {
        block()
    }
}