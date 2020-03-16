package com.zhan.ktarmor.login.ui

import android.app.Application
import android.content.Context
import android.os.Build
import android.widget.Button
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.ui.LoginActivity
import com.zhan.ktwing.ext.logd
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import com.zhan.ktarmor.MainActivity
import androidx.test.rule.ActivityTestRule
import org.junit.Rule


/**
 *  author: HyJame
 *  date:   2020-03-15
 *  desc:   TODO
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class LoginActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    private lateinit var scenario: ActivityScenario<LoginActivity>

    private lateinit var loginActivity: LoginActivity

    @Before
    fun init() {
        scenario = ActivityScenario.launch(LoginActivity::class.java)
        assertNotNull(scenario)
        ShadowLog.stream = System.out

    }

    @Test
    fun test_resources() {
        val application = getApplicationContext<Context>()
        val appName = application.getString(R.string.app_name)
        assertEquals("KtArmor", appName)
    }

    @Test
    fun test_activity_test_rule() {
        assertNotNull(activityRule.activity)

        val loginActivity = activityRule.activity
        loginActivity.findViewById<Button>(R.id.mBtnLogin).performClick()
    }

    @Test
    fun test_activity_scenario() {
        scenario.onActivity {
            it.findViewById<Button>(R.id.mBtnRegister).performClick()
        }
    }
}