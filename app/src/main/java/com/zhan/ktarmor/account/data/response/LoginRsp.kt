package com.zhan.ktarmor.account.data.response

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
data class LoginRsp(
        var icon: String,
        var type: String,
        var collectIds: List<Int>,
        var username: String
)