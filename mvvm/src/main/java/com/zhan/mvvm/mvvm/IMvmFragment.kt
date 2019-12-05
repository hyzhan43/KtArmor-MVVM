package com.zhan.mvvm.mvvm

import com.zhan.mvvm.base.IFragment

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
interface IMvmFragment : IFragment, IMvmView {

    fun dataObserver() {}
}