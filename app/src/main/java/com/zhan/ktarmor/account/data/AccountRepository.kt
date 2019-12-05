package com.zhan.ktarmor.account.data

import com.zhan.ktarmor.account.data.response.EmptyRsp
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.ktarmor.common.api.ApiRepository
import com.zhan.ktarmor.common.data.BaseResponse

/**
 * @author  hyzhan
 * @date    2019/5/23
 * @desc    TODO
 */
class AccountRepository : ApiRepository() {

    suspend fun login(account: String, password: String): BaseResponse<LoginRsp> {

        // TODO local DB

        // network
        return launchIO { apiService.login(account, password) }
    }

    suspend fun collect(id: Int):BaseResponse<EmptyRsp> {
        return launchIO { apiService.collectAsync(id) }
    }
}