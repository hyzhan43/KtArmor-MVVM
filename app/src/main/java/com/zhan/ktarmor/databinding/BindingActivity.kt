package com.zhan.ktarmor.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhan.ktarmor.R
import com.zhan.ktarmor.databinding.bean.User
import com.zhan.mvvm.annotation.BindDataBinding
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.base.IActivity
import com.zhan.mvvm.mvvm.IMvmActivity
import kotlinx.android.synthetic.main.activity_binding.*

/**
 *  author: HyJame
 *  date:   2020/6/22
 *  desc:   TODO
 */
class BindingActivity: AppCompatActivity(), IMvmActivity {

    @BindDataBinding
    lateinit var binding: ActivityBindingBinding

    @BindViewModel
    lateinit var viewModel: UserViewModel

    override fun getLayoutId(): Int = R.layout.activity_binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.userViewModel = viewModel

        mBtnChange.setOnClickListener {
            viewModel.getUser()
        }
    }
}