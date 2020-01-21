package com.zhan.ktarmor.account

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.data.AccountRepository
import com.zhan.ktarmor.account.data.response.EmptyRsp
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.ktarmor.common.api.API
import com.zhan.ktarmor.common.data.BaseResponse
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.bean.KResponse
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.mvvm.BaseViewModel
import okhttp3.*
import java.io.IOException

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

        /**
         * Retrofit DSL
         */
        /*quickLaunch<LoginRsp> {

            onStart { showLoading() }

            request { repository.login(account, password) }

            onSuccess { loginData.value = it }
        }*/

        val client = OkHttpClient()

        val body = FormBody.Builder()
                .add("username", account)
                .add("password", password)
                .build()

        val request = Request.Builder()
                .url(API.BASE_URL + API.LOGIN)
                .post(body)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showToast(e.message ?: "网络异常")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseString = response.body()?.string() ?: ""

                    val baseResponse: BaseResponse<LoginRsp> = Gson().fromJson(responseString, object : TypeToken<BaseResponse<LoginRsp>>() {}.type)

                    if (baseResponse.isSuccess()) {
                        loginData.postValue(baseResponse.data)
                    } else {
                        postShowToast(baseResponse.errorMsg)
                    }
                } else {
                    postShowToast(response.message())
                }
            }
        })

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