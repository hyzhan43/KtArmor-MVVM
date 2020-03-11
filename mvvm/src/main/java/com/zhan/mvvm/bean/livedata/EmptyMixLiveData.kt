package com.zhan.mvvm.bean.livedata

/**
 *  author:  HyJame
 *  date:    2020/2/28
 *  desc:    (测试中)数据 + Empty(空) 组合成的 LiveData
 */
class EmptyMixLiveData<T> : ComplexLiveData<T, Empty>() {

    fun postErrorValue() {
        super.postErrorValue(Empty())
    }
}


