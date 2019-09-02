package com.zhan.mvvm.base

import com.zhan.mvvm.common.IOScope
import kotlinx.coroutines.*

/**
 *  @author: HyJame
 *  @date:   2019-09-02
 *  @desc:   TODO
 */
abstract class ScopeActivity : BaseActivity(){

    val mainScope by lazy { MainScope() }
    val ioScope by lazy { IOScope() }

    fun launchIO(block: () -> Unit) {
        ioScope.launch {
            block()
        }
    }

    fun launchUI(block: () -> Unit) {
        MainScope().launch {
            block()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
        ioScope.cancel()
    }
}