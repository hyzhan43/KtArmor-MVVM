package com.zhan.mvvm.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 *  @author: HyJame
 *  @date:   2019-09-02
 *  @desc:   TODO
 */
@Suppress("FunctionName")
fun IOScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)