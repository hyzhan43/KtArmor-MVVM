package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.common.ViewModelFactory
import com.zhan.mvvm.mvvm.IMvmActivity
import java.lang.reflect.Field

/**
 *  @author: HyJame
 *  @date:   2019-11-21
 *  @desc:   IMvmActivity 代理类具体实现
 */
class MvmActivityDelegateImpl(private var activity: Activity?)
    : ActivityDelegateImpl(activity), IMvmActivity {

    override fun getLayoutId(): Int = 0

    private var iMvmActivity: IMvmActivity? = activity as IMvmActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
        iMvmActivity?.dataObserver()
    }

    /**
     *  根据 @BindViewModel 注解, 查找注解标示的变量（ViewModel）
     *  并且 创建 ViewModel 实例, 注入到变量中
     */
    private fun initViewModel() {
        activity?.apply {
            javaClass.fields
                    .filter { field -> field.isAnnotationPresent(BindViewModel::class.java) }
                    .forEach { field -> injectViewModel(field, this) }
        }
    }

    private fun injectViewModel(field: Field?, activity: Activity) {
        field?.apply {
            isAccessible = true
            val viewModel = ViewModelFactory.createViewModel(field, activity)
            set(activity, viewModel)
        }
    }

    /**
     *  防止内存泄漏
     */
    override fun onDestroy() {
        super.onDestroy()
        this.activity = null
        this.iMvmActivity = null
    }
}