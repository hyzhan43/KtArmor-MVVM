package com.zhan.ktarmor.account

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.data.AccountRepository
import com.zhan.ktarmor.account.data.response.EmptyRsp
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.mvvm.BaseViewModel

/**
 * @author  hyzhan
 * @date    2019/5/23
 * @desc    TODO
 */
class AccountViewModel : BaseViewModel<AccountRepository>() {

    val loginData = MutableLiveData<LoginRsp>()
    val collectData = MutableLiveData<EmptyRsp>()

    fun login(account: String, password: String) {

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            sharedData.value = SharedData(strRes = R.string.account_or_password_empty, type = SharedType.RESOURCE)
            return
        }

        quickLaunch<LoginRsp> {

            onStart { showLoading() }

            request { repository.login(account, password) }

            onSuccess { loginData.value = it }
        }
    }

    fun collect() {
        launchUI({
            showLoading()
            repository.collect(9014).execute({
                collectData.value = it
            })
        })
    }
}