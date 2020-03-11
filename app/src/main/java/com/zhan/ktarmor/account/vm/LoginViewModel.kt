package com.zhan.ktarmor.account.vm

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.data.AccountRepository
import com.zhan.ktarmor.account.data.response.EmptyRsp
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.mvvm.bean.livedata.EmptyMixLiveData
import com.zhan.mvvm.mvvm.BaseViewModel

/**
 * @author  hyzhan
 * @date    2019/5/23
 * @desc    TODO
 */
class LoginViewModel : BaseViewModel<AccountRepository>() {


    val loginSuccessData = MutableLiveData<LoginRsp>()
    val loginFailData = MutableLiveData<EmptyRsp>()

    val loginData = EmptyMixLiveData<LoginRsp>()

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
         *
         *  两者选其一
         *
         */
        quickLaunch<LoginRsp> {

            onStart { showLoading() }

            request { repository.login(account, password) }

            onSuccess {
                // 获取 loginRsp
                loginData.postSuccessValue(it)
            }


//            onSuccessRsp {
//            }

            onFailure { loginData.postErrorValue() }
        }


    }

}