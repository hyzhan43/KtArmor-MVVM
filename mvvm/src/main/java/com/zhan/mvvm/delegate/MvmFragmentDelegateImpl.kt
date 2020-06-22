package com.zhan.mvvm.delegate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.common.ViewModelFactory
import java.lang.reflect.Field

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   IMvmFragment 代理类具体实现
 */
class MvmFragmentDelegateImpl(private var fm: FragmentManager?,
                              private var fragment: Fragment?)
    : FragmentDelegateImpl(fm, fragment), IMvmFragment {

    override fun getLayoutId(): Int = 0

    private var iMvmFragment: IMvmFragment? = fragment as IMvmFragment

    override fun onCreated(savedInstanceState: Bundle?) {
        super.onCreated(savedInstanceState)

        initViewModel()
        iMvmFragment?.dataObserver()
    }

    /**
     *  根据 @BindViewModel 注解, 查找注解标示的变量（ViewModel）
     *  并且 创建 ViewModel 实例, 注入到变量中
     */
    private fun initViewModel() {
        fragment?.apply {
            javaClass.fields
                    .filter { field -> field.isAnnotationPresent(BindViewModel::class.java) }
                    .forEach { field -> injectViewModel(field, this) }
        }
    }

    private fun injectViewModel(field: Field?, fragment: Fragment) = field?.apply {
        isAccessible = true
        val viewModel = ViewModelFactory.createViewModel(field, fragment)
        set(fragment, viewModel)
    }

    /**
     *  防止内存泄漏
     */
    override fun onDestroyed() {
        super.onDestroyed()
        this.fm = null
        this.fragment = null
        this.iMvmFragment = null
    }
}