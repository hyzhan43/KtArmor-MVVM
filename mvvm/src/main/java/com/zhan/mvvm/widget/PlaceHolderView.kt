package com.zhan.mvvm.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.gone
import com.zhan.ktwing.ext.visible
import com.zhan.mvvm.R
import kotlinx.android.synthetic.main.k_layout_empty.view.*


/**
 * @author  hyzhan
 * @date    2019/5/20
 * @desc    TODO
 */
class PlaceHolderView @JvmOverloads constructor(context: Context,
                                                attrs: AttributeSet? = null,
                                                defStyle: Int = 0)
    : LinearLayout(context, attrs, defStyle), PlaceHolder {

    private val defaultDrawable = R.drawable.ic_frown
    private val defaultColor = Color.GRAY

    var emptyDrawable: Int = 0
    var errorDrawable: Int = 0

    var loadingColor = 0
        set(color) {
            field = color
            mLoading.innerColor = color
            mLoading.outerColor = color
        }

    var emptyText: String? = null
        set(value) {
            field = value ?: context.getString(R.string.prompt_empty)
        }

    var errorText: String? = null
        set(value) {
            field = value ?: context.getString(R.string.prompt_error)
        }

    var loadingText: String? = null
        set(value) {
            field = value ?: context.getString(R.string.prompt_loading)
        }

    private val mBindViews by lazy { ArrayList<View>() }

    init {
        View.inflate(context, R.layout.k_layout_empty, this)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.PlaceHolderView, defStyle, 0)

        with(typeArray) {
            emptyDrawable = getResourceId(R.styleable.PlaceHolderView_emptyDrawable, defaultDrawable)
            errorDrawable = getResourceId(R.styleable.PlaceHolderView_errorDrawable, defaultDrawable)

            loadingColor = getColor(R.styleable.PlaceHolderView_loadingColor, defaultColor)

            emptyText = getString(R.styleable.PlaceHolderView_emptyText)
            errorText = getString(R.styleable.PlaceHolderView_errorText)
            loadingText = getString(R.styleable.PlaceHolderView_loadingText)

            recycle()
        }

    }

    fun add(view: View): PlaceHolderView {
        mBindViews.add(view)
        return this
    }

    private fun changeBindViewState(visible: Int) {
        val views = mBindViews

        views.isNotEmpty().let {
            views.forEach { view ->
                view.visibility = visible
            }
        }
    }


    override fun triggerEmpty() {
        mLoading.gone()
        mLoading.stop()
        mIvImage.visible()
        mIvImage.setImageResource(emptyDrawable)
        mTvTips.text = emptyText

        this.visible()
        changeBindViewState(View.GONE)
    }

    override fun triggerNetError() {
        mLoading.gone()
        mLoading.stop()
        mIvImage.visible()
        mIvImage.setImageResource(errorDrawable)
        mTvTips.text = errorText

        this.visible()
        changeBindViewState(View.GONE)
    }


    override fun triggerError(@StringRes strRes: Int) {
        toast(strRes)

        this.visible()
        changeBindViewState(View.GONE)
    }

    override fun triggerError(str: String) {
        toast(str)

        this.visible()
        changeBindViewState(View.GONE)
    }

    override fun triggerLoading() {
        mIvImage.gone()
        mLoading.visible()
        mLoading.start()
        mTvTips.text = loadingText

        this.visible()
        changeBindViewState(View.GONE)
    }

    override fun triggerOk() {
        this.gone()
        changeBindViewState(View.VISIBLE)
    }

    override fun triggerOkOrEmpty(isOk: Boolean) {
        if (isOk) {
            triggerOk()
        } else {
            triggerEmpty()
        }
    }
}