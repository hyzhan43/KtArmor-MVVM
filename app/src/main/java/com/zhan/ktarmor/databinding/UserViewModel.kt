package com.zhan.ktarmor.databinding

import androidx.lifecycle.MutableLiveData
import com.zhan.ktarmor.databinding.bean.User
import com.zhan.mvvm.mvvm.BaseViewModel
import com.zhan.mvvm.mvvm.livedata.CommonLiveData

/**
 *  author: HyJame
 *  date:   2020/6/22
 *  desc:   TODO
 */
class UserViewModel : BaseViewModel<UserRepository>() {

    val userLiveData = MutableLiveData<User>()

    fun getUser() {
        userLiveData.postValue(User("Hello", "HyJame"))
    }
}