package com.zhan.ktarmor.account

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.data.AccountRepository
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.ktarmor.common.data.BaseResponse
import com.zhan.mvvm.common.SharedData
import com.zhan.mvvm.mvvm.BaseViewModel

/**
 * @author  hyzhan
 * @date    2019/5/23
 * @desc    TODO
 */
class AccountViewModel : BaseViewModel<AccountRepository>() {

    val loginData = MutableLiveData<BaseResponse<LoginRsp>>()

    override fun bindRepository(): AccountRepository = AccountRepository()

    fun login(account: String, password: String) {

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            sharedData.value = SharedData(strRes = R.string.account_or_password_empty)
        } else {
            launchUI { loginData.value = repository.login(account, password) }
        }
    }
}