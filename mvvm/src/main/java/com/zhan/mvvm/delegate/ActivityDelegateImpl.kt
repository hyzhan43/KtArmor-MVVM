package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle
import com.zhan.mvvm.base.IActivity

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   activity 代理实现类
 */
open class ActivityDelegateImpl(private var activity: Activity?) : ActivityDelegate {

    private var iActivity: IActivity? = activity as IActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        iActivity?.apply {
            // 在界面未初始化之前调用的初始化窗口
            initWidows()

            if (initArgs(activity?.intent?.extras)) {
                activity?.setContentView(getLayoutId())

                initBefore()
                initView()
                initListener()
                initData()
            } else {
                activity?.finish()
            }
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
        this.activity = null
        this.iActivity = null
    }

    override fun onSaveInstanceState(activity: Activity?, outState: Bundle?) {

    }
}