package com.zhan.hy.mvvm.wiget

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.zhan.hy.mvvm.R
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

    private var emptyDrawable: Int = 0
    private var errorDrawable: Int = 0

    private var loadingColor = 0

    private var emptyText: Int = 0
    private var errorText: Int = 0
    private var loadingText: Int = 0


    private val mBindViews by lazy { ArrayList<View>() }

    init {
        View.inflate(context, R.layout.layout_empty, this)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, defStyle, 0)

        with(typeArray) {
            emptyDrawable = getResourceId(R.styleable.EmptyView_emptyDrawable, defaultDrawable)
            errorDrawable = getResourceId(R.styleable.EmptyView_errorDrawable, defaultDrawable)

            loadingColor = getColor(R.styleable.EmptyView_loadingColor, defaultColor)

            emptyText = getResourceId(R.styleable.EmptyView_emptyText, R.string.prompt_empty)
            errorText = getResourceId(R.styleable.EmptyView_errorText, R.string.prompt_error)
            loadingText = getResourceId(R.styleable.EmptyView_loadingText, R.string.prompt_loading)

            recycle()
        }

        // 设置默认颜色
        setProgressColor(loadingColor)
    }

    fun add(view: View): EmptyView {
        mBindViews.add(view)
        return this
    }

    fun setProgressColor(@ColorInt color: Int) {
        mPbLoading.indeterminateDrawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
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
        mTvTips.setText(emptyText)

        this.visibility = View.VISIBLE
        changeBindViewState(View.GONE)
    }

    override fun triggerNetError() {

        mPbLoading.visibility = View.GONE
        mIvImage.setImageResource(errorDrawable)
        mTvTips.setText(errorText)

        this.visibility = View.VISIBLE
        changeBindViewState(View.GONE)
    }


    override fun triggerError(strRes: Int) {
        // TODO toast显示

        this.visibility = View.VISIBLE
        changeBindViewState(View.GONE)
    }

    override fun triggerLoading() {
        mIvImage.visibility = View.GONE
        mPbLoading.visibility = View.VISIBLE
        mTvTips.setText(loadingText)

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