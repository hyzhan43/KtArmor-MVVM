package com.zhan.mvvm.widget

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.zhan.mvvm.R
import com.zhan.mvvm.ext.Toasts.toast
import kotlinx.android.synthetic.main.layout_empty.view.*


/**
 * 基础的占位布局接口定义
 *
 * @author  hyzhan
 * @date    2019/5/20
 * @desc    TODO
 */
class EmptyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : LinearLayout(context, attrs, defStyle), PlaceHolderView {

    private val defaultDrawable = R.drawable.ic_empty_view
    private val defaultColor = Color.GRAY

    var emptyDrawable: Int = 0
    var errorDrawable: Int = 0

    var loadingColor = 0
        set(color) {
            mPbLoading.indeterminateDrawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
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
        View.inflate(context, R.layout.layout_empty, this)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, defStyle, 0)

        with(typeArray) {
            emptyDrawable = getResourceId(R.styleable.EmptyView_emptyDrawable, defaultDrawable)
            errorDrawable = getResourceId(R.styleable.EmptyView_errorDrawable, defaultDrawable)

            loadingColor = getColor(R.styleable.EmptyView_loadingColor, defaultColor)

            emptyText = getString(R.styleable.EmptyView_emptyText)
            errorText = getString(R.styleable.EmptyView_errorText)
            loadingText = getString(R.styleable.EmptyView_loadingText)

            recycle()
        }

    }

    fun add(view: View): EmptyView {
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
        mPbLoading.visibility = View.GONE
        mIvImage.setImageResource(emptyDrawable)
        mTvTips.text = emptyText

        this.visibility = View.VISIBLE
        changeBindViewState(View.GONE)
    }

    override fun triggerNetError() {

        mPbLoading.visibility = View.GONE
        mIvImage.setImageResource(errorDrawable)
        mTvTips.text = errorText

        this.visibility = View.VISIBLE
        changeBindViewState(View.GONE)
    }


    override fun triggerError(@StringRes strRes: Int) {
        toast(strRes)

        this.visibility = View.VISIBLE
        changeBindViewState(View.GONE)
    }

    override fun triggerError(str: String) {
        toast(str)

        this.visibility = View.VISIBLE
        changeBindViewState(View.GONE)
    }

    override fun triggerLoading() {
        mIvImage.visibility = View.GONE
        mPbLoading.visibility = View.VISIBLE
        mTvTips.text = loadingText

        this.visibility = View.VISIBLE
        changeBindViewState(View.GONE)
    }

    override fun triggerOk() {
        this.visibility = View.GONE
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