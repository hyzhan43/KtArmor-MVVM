package com.zhan.ktarmor

import android.app.Application
import com.zhan.ktarmor.common.MyRetrofitConfig
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
        KtArmor.init(this, MyRetrofitConfig())
    }
}