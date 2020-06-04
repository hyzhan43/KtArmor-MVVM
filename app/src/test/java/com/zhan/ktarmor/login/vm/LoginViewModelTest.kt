package com.zhan.ktarmor.login.vm

import android.text.TextUtils
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.data.AccountRepository
import com.zhan.ktarmor.account.data.response.LoginRsp
import com.zhan.ktarmor.account.vm.LoginViewModel
import com.zhan.ktarmor.common.data.BaseResponse
import com.zhan.mvvm.mvvm.livedata.CommonLiveData
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runners.Parameterized

/**
 *  author: HyJame
 *  date:   2020-03-15
 *  desc:   TODO
 */
class LoginViewModelTest {

    @SpyK
    private var loginViewModel = LoginViewModel()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun test_login_view_model_with_empty_password() {
        val account = "123"
        val password = ""

        mockkStatic(TextUtils::class)
        every { TextUtils.isEmpty(any()) } returns true
        every { loginViewModel.showToast(R.string.account_or_password_empty) } just Runs

        loginViewModel.login(account, password)

        verify { loginViewModel.showToast(R.string.account_or_password_empty) }
    }

    @Test
    fun test_login_view_model_with_empty_account() {
        val account = ""
        val password = "123"

        mockkStatic(TextUtils::class)
        every { TextUtils.isEmpty(any()) } returns true
        every { loginViewModel.showToast(R.string.account_or_password_empty) } just Runs

        loginViewModel.login(account, password)

        verify { loginViewModel.showToast(R.string.account_or_password_empty) }
    }

    @Test
    fun test_login_view_model() = runBlocking {
        // prepare
        val account = "123"
        val password = "123"

        mockkStatic(TextUtils::class)
        every { TextUtils.isEmpty(any()) } returns false
        // test
        loginViewModel.login(account, password)
        //assert
        verify { loginViewModel.superLaunchRequest(any<CommonLiveData<LoginRsp>>(), any()) }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}