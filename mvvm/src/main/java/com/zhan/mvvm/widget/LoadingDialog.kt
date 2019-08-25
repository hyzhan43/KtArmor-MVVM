package com.zhan.mvvm.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.zhan.mvvm.R
import kotlinx.android.synthetic.main.k_layout_loading.*


class LoadingDialog private constructor() : DialogFragment() {

    private lateinit var message: String

    private lateinit var manager: FragmentManager

    private lateinit var dialogTag: String

    companion object {
        fun create(manager: FragmentManager,
                   message: String = "Loading...",
                   tag: String = "loadingDialog"): LoadingDialog {

            val loadingDialog = LoadingDialog()
            loadingDialog.manager = manager
            loadingDialog.dialogTag = tag
            loadingDialog.message = message
            return loadingDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        return inflater.inflate(R.layout.k_layout_loading, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mLoading.start()
        mTvTips.text = message
    }

    fun show() {
        this.show(manager, dialogTag)
    }

    fun hide(){
        this.dismiss()
    }

    override fun onDestroyView() {
        mLoading.stop()
        super.onDestroyView()
    }
}