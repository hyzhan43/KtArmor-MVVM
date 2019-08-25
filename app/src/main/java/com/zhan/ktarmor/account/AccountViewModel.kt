package com.zhan.ktarmor.account

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.data.AccountRepository
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.mvvm.BaseViewModel
import kotlinx.coroutines.delay

/**
 * @author  hyzhan
 * @date    2019/5/23
 * @desc    TODO
 */
class AccountViewModel : BaseViewModel<AccountRepository>() {

    val loginData = MutableLiveData<LoginRsp>()

    fun login(account: String, password: String) {

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            sharedData.value = SharedData(strRes = R.string.account_or_password_empty, type = SharedType.RESOURCE)
        } else {
            launchUI({
                showLoading()
                repository.login(account, password).execute({ loginRsp ->
                    loginRsp?.let { loginData.value = it }
                })
            })
        }
    }
}