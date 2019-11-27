package com.zhan.mvvm.base

/**
 *  author: HyJame
 *  date:   2019-11-27
 *  desc:   TODO
 */
interface IFragment {

    fun getLayoutId(): Int

    fun initBefore() {}

    fun initView() {}

    fun initListener() {}

    fun initData() {}
}