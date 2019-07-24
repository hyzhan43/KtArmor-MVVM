package com.zhan.ktarmor.common

import com.zhan.ktarmor.common.api.API
import com.zhan.mvvm.http.BaseRetrofitConfig

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
class MyRetrofitConfig : BaseRetrofitConfig() {

    override val baseUrl: String
        get() = API.BASE_URL
}