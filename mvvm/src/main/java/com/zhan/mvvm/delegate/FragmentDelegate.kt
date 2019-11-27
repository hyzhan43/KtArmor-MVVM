package com.zhan.mvvm.delegate

import android.content.Context
import android.os.Bundle
import android.view.View

/**
 *  @author: HyJame
 *  @date:   2019-11-27
 *  @desc:   TODO
 */
interface FragmentDelegate {

    fun onAttached(context: Context)

    fun onCreated(savedInstanceState: Bundle?)

    fun onViewCreated(v: View, savedInstanceState: Bundle?)

    fun onActivityCreate(savedInstanceState: Bundle?)

    fun onStarted()

    fun onResumed()

    fun onPaused()

    fun onStopped()

    fun onViewDestroyed()

    fun onDestroyed()

    fun onDetached()

}