package com.zhan.mvvm.base

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
interface IView {

    fun getLayoutId(): Int

    fun initBefore() {}

    fun initView() {}

    fun initListener() {}

    fun initData() {}
}