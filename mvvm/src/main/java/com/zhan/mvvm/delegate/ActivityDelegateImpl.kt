package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   TODO
 */
class ActivityDelegateImpl(private val iActivity: IActivity) : ActivityDelegate {

    private val mActivity = iActivity as Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        // 在界面未初始化之前调用的初始化窗口
        iActivity.initWidows()

        if (iActivity.initArgs(mActivity.intent.extras)) {
            mActivity.setContentView(iActivity.getLayoutId())

            iActivity.initBefore()
            iActivity.initView()
            iActivity.initListener()
            iActivity.initData()
        } else {
            mActivity.finish()
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