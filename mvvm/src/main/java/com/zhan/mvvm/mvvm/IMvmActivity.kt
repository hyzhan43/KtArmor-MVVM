package com.zhan.mvvm.mvvm

import com.zhan.mvvm.base.IActivity

/**
 * author：  HyZhan
 * create：  2019/8/7
 * desc：    TODO
 */
interface IMvmActivity : IActivity, IMvmView {

    fun dataObserver() {}
}