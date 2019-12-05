package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle
import com.zhan.mvvm.base.IActivity

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   TODO
 */
open class ActivityDelegateImpl(private val activity: Activity) : ActivityDelegate {

    private val iActivity = activity as IActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        // 在界面未初始化之前调用的初始化窗口
        iActivity.initWidows()

        if (iActivity.initArgs(activity.intent.extras)) {
            activity.setContentView(iActivity.getLayoutId())

            iActivity.initBefore()
            iActivity.initView()
            iActivity.initListener()
            iActivity.initData()
        } else {
            activity.finish()
        }
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }

    override fun onSaveInstanceState(activity: Activity?, outState: Bundle?) {

    }
}