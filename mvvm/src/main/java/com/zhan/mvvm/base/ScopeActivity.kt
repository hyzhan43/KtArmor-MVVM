package com.zhan.mvvm.base

import com.zhan.mvvm.common.IOScope
import kotlinx.coroutines.*

/**
 *  @author: HyJame
 *  @date:   2019-09-02
 *  @desc:   TODO
 */
interface ScopeActivity {

    var mainScope: CoroutineScope
    var ioScope: CoroutineScope

    fun createScope() {
        mainScope = MainScope()
        ioScope = IOScope()
    }

    fun launchIO(block: () -> Unit) = ioScope.launch { block() }

    fun launchUI(block: () -> Unit) = mainScope.launch { block() }

    fun cancelScope() {
        mainScope.cancel()
        ioScope.cancel()
    }
}