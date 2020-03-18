package com.zhan.ktarmor.login.ui

import android.content.Context
import android.os.Build
import android.widget.Button
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.zhan.ktarmor.MainActivity
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.ui.LoginActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog


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

    @Test
    fun test_hello_world(){
        val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        val hello = mainActivity.findViewById<Button>(R.id.btnCollect)
        hello.performClick()
    }
}