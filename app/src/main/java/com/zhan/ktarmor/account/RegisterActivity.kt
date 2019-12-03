package com.zhan.ktarmor.account

import androidx.appcompat.app.AppCompatActivity
import com.zhan.ktarmor.R
import com.zhan.mvvm.delegate.IMvmActivity
import kotlinx.android.synthetic.main.activity_register.*

/**
 *  @author: HyJame
 *  @date:   2019-11-21
 *  @desc:   TODO
 */
class RegisterActivity : AppCompatActivity(), IMvmActivity {

//    @BindViewModel
//    lateinit var viewModel: RegisterViewModel

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun initData() {
        super.initData()

        btnShowToast.setOnClickListener {
//            viewModel.showToast("Hello World")
            showToast("hello")
        }
    }
}