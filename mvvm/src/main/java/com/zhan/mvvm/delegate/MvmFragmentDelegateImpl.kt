package com.zhan.mvvm.delegate

import android.os.Bundle
import android.view.View
import androidx.collection.LruCache
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.common.ViewModelFactory
import java.lang.reflect.Field

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   IMvmFragment 代理类具体实现
 */
class MvmFragmentDelegateImpl(private val fm: FragmentManager, private val fragment: Fragment) :
    FragmentDelegateImpl(fm, fragment), IMvmFragment {

    override fun getLayoutId(): Int = 0

    private val iMvmFragment = fragment as IMvmFragment

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(v, savedInstanceState)
        iMvmFragment.dataObserver()
    }

    /**
     *  根据 @BindViewModel 注解, 查找注解标示的变量（ViewModel）
     *  并且 创建 ViewModel 实例, 注入到变量中
     */
    private fun initViewModel() {
        fragment.javaClass.fields
            .filter { it.isAnnotationPresent(BindViewModel::class.java) }
            .forEach {
                it?.apply {
                    isAccessible = true
                    set(fragment, getViewModel(this))
                }
            }
    }


    private fun getViewModel(field: Field): ViewModel {
        return ViewModelFactory.createViewModel(fragment, field)
    }
}