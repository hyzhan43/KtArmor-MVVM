package com.zhan.ktarmor.account.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.ktarmor.account.data.AccountRepository
import com.zhan.mvvm.mvvm.BaseViewModel

/**
 *  @author: HyJame
 *  @date:   2019-11-22
 *  @desc:   TODO
 */
class RegisterViewModel : BaseViewModel<AccountRepository>() {

    val registerData = MutableLiveData<String>()

    fun register(account: String, password: String) {
        registerData.value = "注册成功"
    }

}