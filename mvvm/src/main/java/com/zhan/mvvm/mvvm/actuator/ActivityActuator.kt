package com.zhan.mvvm.mvvm.actuator

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.constant.Const
import com.zhan.mvvm.mvvm.IMvmView

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
class ActivityActuator<R>(private val owner: LifecycleOwner,
                          private val liveData: CommonLiveData<R>
) : BaseActivityActuator<R>(owner, liveData) {


}