package com.zhan.ktarmor.account.vm

import android.text.TextUtils
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.data.AccountRepository
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.mvvm.mvvm.actuator.CommonLiveData
import com.zhan.mvvm.mvvm.BaseViewModel

/**
 * @author  hyzhan
 * @date    2019/5/23
 * @desc    TODO
 */
class LoginViewModel : BaseViewModel<AccountRepository>() {

    val loginData = CommonLiveData<LoginRsp>()

    fun login(account: String, password: String) {

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            showToast(R.string.account_or_password_empty)
            return
        }

        /**
         *  DSL方式发起网络请求
         *
         *  若需要 code, message, 则调用 onSuccessRsp 方法
         *  若需要 loginRsp, 则调用 onSuccess 方法
         */
//        quickLaunch<LoginRsp> {
//
//            request { repository.login(account, password) }
//
//            onSuccess { loginData.value = it }
//
//            onFailure { loginData.failureMessage = it }
//
//            onException { loginData.exception = it }
//        }

        /**
         * 等同上面 quickLaunch
         */
        superLaunch(loginData) {
            request { repository.login(account, password) }
        }
    }

    fun loginByTest(account: String, password: String) {
        superLaunch(loginData) {
            request { repository.testLogin(account, password) }
        }
    }

    fun loginByTestService(account: String, password: String) {
        superLaunch(loginData) {
            request { repository.serviceTestLogin(account, password) }
        }
    }
}