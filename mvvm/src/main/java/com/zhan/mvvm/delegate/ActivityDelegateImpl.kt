package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhan.mvvm.annotation.BindDataBinding
import com.zhan.mvvm.base.IActivity
import java.lang.reflect.Field

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
                initContentView()

                initBefore()
                initView()
                initListener()
                initData()
            } else {
                activity?.finish()
            }
        }
    }

    /**
     *  根据 @BindDataBinding 注解, 查找注解标示的变量（DataBinding）
     *  并且 创建 DataBinding 实例, 注入到变量中
     */
    private fun initContentView() {
        activity?.apply {
            val fields = javaClass.fields.filter { field -> field.isAnnotationPresent(BindDataBinding::class.java) }
            if (fields.isNotEmpty()) {
                injectDataBinding(fields[0], this)
            } else {
                iActivity?.let { this.setContentView(it.getLayoutId()) }
            }
        }
    }

    private fun injectDataBinding(field: Field?, activity: Activity) {
        iActivity?.let {
            field?.apply {
                isAccessible = true
                val dataBinding = DataBindingUtil.setContentView<ViewDataBinding>(activity, it.getLayoutId())
                dataBinding.lifecycleOwner = activity as AppCompatActivity
                set(activity, dataBinding)
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