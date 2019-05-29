package com.zhan.ktarmor

import android.app.Application
import com.zhan.ktarmor.common.api.API
import com.zhan.mvvm.KtArmor

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
class BaseApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        // 初始化KtArmor
        KtArmor.build {
            context = applicationContext
            baseUrl = API.BASE_URL

//             选择配置
//            readTime = 20L
//            writeTime = 30L
//            connectTime = 5L
        }
    }
}