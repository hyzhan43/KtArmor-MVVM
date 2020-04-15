package com.zhan.ktarmor.login.vm

import android.text.TextUtils
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.vm.LoginViewModel
import io.mockk.*
import io.mockk.impl.annotations.SpyK
import org.junit.Before
import org.junit.Test

/**
 *  author: HyJame
 *  date:   2020-03-15
 *  desc:   TODO
 */
class LoginViewModelTest {

    @SpyK
    private var loginViewModel = LoginViewModel()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
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

}