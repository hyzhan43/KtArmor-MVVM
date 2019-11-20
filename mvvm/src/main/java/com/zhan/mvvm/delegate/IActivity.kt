package com.zhan.mvvm.delegate

import android.os.Bundle

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   TODO
 */
interface IActivity {

    fun initWidows() {}

    fun initArgs(extras: Bundle?): Boolean = true

    fun getLayoutId(): Int

    fun initBefore() {}

    fun initView() {}

    fun initData() {}

    fun initListener() {}
}