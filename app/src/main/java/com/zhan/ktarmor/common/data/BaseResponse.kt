package com.zhan.ktarmor.common.data

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
data class BaseResponse<T>(var data: T?, var errorCode: Int = -1, var errorMsg: String = "")