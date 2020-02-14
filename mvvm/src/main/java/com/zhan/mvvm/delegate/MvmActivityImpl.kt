package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.common.ViewModelFactory
import com.zhan.mvvm.mvvm.IMvmActivity
import java.lang.reflect.Field

/**
 *  @author: HyJame
 *  @date:   2019-11-21
 *  @desc:   IMvmActivity 代理类具体实现
 */
class MvmActivityImpl(private val activity: Activity)
    : ActivityDelegateImpl(activity), IMvmActivity {

    override fun getLayoutId(): Int = 0

    private val iMvmActivity = activity as IMvmActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
        iMvmActivity.dataObserver()
    }

    /**
     *  根据 @BindViewModel 注解, 查找注解标示的变量（ViewModel）
     *  并且 创建 ViewModel 实例, 注入到变量中
     */
    private fun initViewModel() {
        activity.javaClass.fields
                .filter { it.isAnnotationPresent(BindViewModel::class.java) }
                .getOrNull(0)
                ?.apply {
                    isAccessible = true
                    set(activity, getViewModel(this))
                }
    }

    private fun getViewModel(field: Field): ViewModel {
        return ViewModelFactory.createViewModel(activity, field)
    }
}