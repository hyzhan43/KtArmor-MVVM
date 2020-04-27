package com.zhan.mvvm.base

import android.os.Bundle
import android.util.LruCache
import com.zhan.mvvm.delegate.ActivityDelegate

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   对应  ActivityDelegateImpl 代理实现类
 */
interface IActivity : IView {

    fun initWidows() {}

    fun initArgs(extras: Bundle?): Boolean = true
}