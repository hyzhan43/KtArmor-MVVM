package com.zhan.ktarmor

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.zhan.ktarmor.BaseTest.onClick
import com.zhan.ktarmor.BaseTest.onEditView
import com.zhan.ktarmor.BaseTest.onToast
import com.zhan.ktarmor.account.ui.LoginActivity

/**
 *  author: HyJame
 *  date:   2019-11-28
 *  desc:   登录页面——登录流程测试
 */
@RunWith(AndroidJUnit4::class)
class LoginProcessTest {

    private val account: String = "1142948328"
    private val password: String = "123456"

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(LoginActivity::class.java)

    private lateinit var idlingResource: IdlingResource

    @Before
    fun setUp() {
        idlingResource = activityTestRule.activity.mIdlingResource
    }

    @After
    fun release() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun test_error_account_or_password() {
        onEditView(R.id.mTieAccount, account)
        onEditView(R.id.mTiePassword, "123")

        IdlingRegistry.getInstance().register(idlingResource)
        onClick(R.id.mBtnLogin)
        onToast(activityTestRule.activity, "账号密码不匹配！")
    }

    @Test
    fun test_login_success_by_myself() {
        onEditView(R.id.mTieAccount, account)
        onEditView(R.id.mTiePassword, password)

        IdlingRegistry.getInstance().register(idlingResource)
        onClick(R.id.mBtnLogin)
        onToast(activityTestRule.activity, "登录成功")
    }
}