package com.zhan.mvvm.base

import android.os.Bundle

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   TODO
 */
interface IActivity: IView{

    fun initWidows() {}

    fun initArgs(extras: Bundle?): Boolean = true
}