package com.zhan.ktarmor.account

import androidx.test.espresso.IdlingResource
import com.zhan.ktwing.ext.logd
import java.util.concurrent.atomic.AtomicBoolean

/**
 *  author: HyJame
 *  date:   2019-11-28
 *  desc:   TODO
 */
class LoginIdlingResource : IdlingResource {

    var callback: IdlingResource.ResourceCallback? = null

    private val mIsIdleNow = AtomicBoolean(true)

    override fun getName(): String = this.javaClass.name

    override fun isIdleNow(): Boolean = mIsIdleNow.get()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    fun setIdleState(isIdleNow: Boolean) {
        mIsIdleNow.set(isIdleNow)
        if (isIdleNow) {
            callback?.onTransitionToIdle()
        }
    }

    fun isIdleState(){
        setIdleState(true)
    }

    fun isNotIdleState(){
        setIdleState(false)
    }
}