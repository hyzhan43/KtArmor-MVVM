package com.zhan.ktarmor.account.data

import com.zhan.ktarmor.account.data.response.EmptyRsp
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.ktarmor.common.api.ServiceFactory
import com.zhan.ktarmor.common.data.BaseResponse

/**
 * @author  hyzhan
 * @date    2019/5/23
 * @desc    TODO
 */
class AccountRepository {

    suspend fun login(account: String, password: String): BaseResponse<LoginRsp> {
        return ServiceFactory.apiService.login(account, password)
    }

    suspend fun testLogin(account: String, password: String): BaseResponse<LoginRsp> {
        return ServiceFactory.apiService.testLogin(account, password)
    }

    suspend fun collect(id: Int):BaseResponse<EmptyRsp> {
        return ServiceFactory.apiService.collectAsync(id)
    }
}