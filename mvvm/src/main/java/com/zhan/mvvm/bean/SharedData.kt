package com.zhan.mvvm.bean

/**
 * @author  hyzhan
 * @date    2019/5/23
 * @desc    共享数据
 */
data class SharedData(val msg: String = "",
                      val strRes: Int = 0,
                      val type: SharedType = SharedType.RESOURCE)