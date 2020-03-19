package com.zhan.ktarmor.common.api

import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.ktarmor.common.data.BaseResponse
import com.zhan.mvvm.annotation.BaseUrl
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * author：  HyJame
 * create：  2020/03/19
 * desc：    TODO
 */
@BaseUrl(API.TEST_URL)
interface TestApiService {

    @POST(API.LOGIN)
    suspend fun testLogin(@Query("username") username: String,
                           @Query("password") password: String): BaseResponse<LoginRsp>

}