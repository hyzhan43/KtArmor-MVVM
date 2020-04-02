package com.zhan.ktarmor

import android.app.Application
import com.zhan.ktarmor.common.MyRetrofitConfig
import com.zhan.ktarmor.common.actuator.MyActivityActuator
import com.zhan.ktarmor.common.actuator.MyLiveDataActuator
import com.zhan.mvvm.KtArmor


/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        with(KtArmor){
//            // 初始化KtArmor
            initRetrofitConfig(MyRetrofitConfig())
            printLog = true
//            //configActivityActuator(MyActivityActuator())
//            //configLiveDataActuator(MyLiveDataActuator())
        }
    }

}