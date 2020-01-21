package com.zhan.ktarmor

import android.app.Activity
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.IsNot.not

/**
 *  author: HyJame
 *  date:   2019-11-28
 *  desc:   TODO
 */
object BaseTest {

    /**
     *  是否显示 toast
     */
    fun onToast(activity: Activity, @StringRes msgRes: Int) {
        onToast(activity, activity.getString(msgRes))
    }

    /**
     *  是否显示 toast
     */
    fun onToast(activity: Activity, msg: String) {
        onView(withText(msg))
            .inRoot(withDecorView(not(activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    /**
     *  编辑输入框内容
     *  1、清除内容
     *  2、写内容
     *  3、关闭软键盘
     */
    fun onEditView(@IdRes idRes: Int, content: String) {
        onView(withId(idRes))
            .perform(clearText(), replaceText(content), closeSoftKeyboard())
    }

    /**
     *  点击控件
     */
    fun onClick(@IdRes idRes: Int) {
        onView(withId(idRes)).perform(click())
    }
}