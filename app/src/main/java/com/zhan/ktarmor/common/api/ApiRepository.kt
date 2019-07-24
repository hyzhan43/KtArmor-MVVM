package com.zhan.ktarmor.common.api

import com.zhan.mvvm.http.RetrofitFactory
import com.zhan.mvvm.mvvm.BaseRepository

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
abstract class ApiRepository : BaseRepository() {

    protected val apiService by lazy {
        RetrofitFactory.instance.create(ApiService::class.java)
    }
}