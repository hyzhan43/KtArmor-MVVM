package com.zhan.mvvm.delegate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhan.mvvm.base.IFragment

/**
 *  author: HyJame
 *  date:   2019-11-27
 *  desc:   TODO
 */
open class FragmentDelegateImpl(
        private var fragmentManager: FragmentManager?,
        private var fragment: Fragment?
) : FragmentDelegate {

    private var iFragment: IFragment? = fragment as IFragment

    override fun onAttached(context: Context) {
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        fragment?.let { fragment ->
            var clazz = fragment.javaClass.superclass

            while (clazz?.name != Fragment::class.java.name) {
                clazz = clazz?.superclass
            }

            val field = clazz.getDeclaredField("mContentLayoutId")

            field.isAccessible = true
            field.set(fragment, iFragment?.getLayoutId())
        }
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        iFragment?.apply {
            initBefore()
            initView()
            initListener()
            initData()
        }
    }

    override fun isAdd(): Boolean = fragment?.isAdded ?: false

    override fun onActivityCreate(savedInstanceState: Bundle?) {
    }

    override fun onStarted() {
    }

    override fun onResumed() {
    }

    override fun onPaused() {
    }

    override fun onStopped() {}

    override fun onSaveInstanceState(outState: Bundle) {}

    override fun onViewDestroyed() {}

    /**
     *  防止内存泄漏
     */
    override fun onDestroyed() {
        this.fragmentManager = null
        this.fragment = null
        this.iFragment = null
    }

    override fun onDetached() {}
}