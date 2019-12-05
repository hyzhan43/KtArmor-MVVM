package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   activity 代理接口
 */
interface ActivityDelegate {

    fun onCreate(savedInstanceState: Bundle?)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onSaveInstanceState(activity: Activity?, outState: Bundle?)

    fun onDestroy()
}