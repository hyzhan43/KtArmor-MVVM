package com.zhan.ktarmor.common.api

import com.zhan.ktarmor.account.data.response.EmptyRsp
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.ktarmor.common.data.BaseResponse
import com.zhan.mvvm.annotation.BaseUrl
import com.zhan.mvvm.annotation.BindUrls
import com.zhan.mvvm.http.intercept.UrlInterceptor.Companion.URL_PREFIX
import retrofit2.http.*

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
@BaseUrl(API.BASE_URL)
@BindUrls(API.TEST_URL, API.AUTH_URL)
interface ApiService {

    @POST(API.LOGIN)
    suspend fun login(@Query("username") username: String,
                      @Query("password") password: String): BaseResponse<LoginRsp>

    @POST(API.LOGIN)
    @Headers(URL_PREFIX + API.TEST_URL)
    suspend fun testLogin(@Query("username") username: String,
                           @Query("password") password: String): BaseResponse<LoginRsp>

    @POST(API.COLLECT)
    suspend fun collectAsync(@Path("id") id: Int): BaseResponse<EmptyRsp>
}