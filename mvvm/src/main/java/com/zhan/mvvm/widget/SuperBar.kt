package com.zhan.mvvm.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.zhan.mvvm.R
import com.zhan.mvvm.ext.getColorRef
import com.zhan.mvvm.ext.gone
import com.zhan.mvvm.ext.visible
import kotlinx.android.synthetic.main.k_superbar_view.view.*

/**
 * author：  HyZhan
 * create：  2019/6/17
 * desc：    TODO
 */
class SuperBar
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyle: Int = 0) : RelativeLayout(context, attrs, defStyle) {

    private val smileDrawable = R.drawable.ic_smile
    private val rightArrow = R.drawable.ic_right
    var leftIcon: Int = 0
    var rightIcon: Int = 0

    private val defaultSize = 14f
    private val defaultColor = context.getColorRef(R.color.grey_700)

    var leftText: String
    var leftTextSize: Float
    var leftTextColor: Int

    var rightText: String
    var rightTextSize: Float
    var rightTextColor: Int

    var showTopLine: Boolean
    var showBottomLine: Boolean

    var showLeftIcon: Boolean
    var showRightIcon: Boolean

    init {
        View.inflate(context, R.layout.k_superbar_view, this)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.SuperBar, defStyle, 0)
        with(typeArray) {
            leftIcon = getResourceId(R.styleable.SuperBar_leftIcon, smileDrawable)
            rightIcon = getResourceId(R.styleable.SuperBar_rightIcon, rightArrow)

            leftText = getString(R.styleable.SuperBar_leftText) ?: ""
            leftTextSize = getDimension(R.styleable.SuperBar_leftTextSize, defaultSize)
            leftTextColor = getColor(R.styleable.SuperBar_leftTextColor, defaultColor)

            rightText = getString(R.styleable.SuperBar_rightText) ?: ""
            rightTextSize = getDimension(R.styleable.SuperBar_rightTextSize, defaultSize)
            rightTextColor = getColor(R.styleable.SuperBar_rightTextColor, defaultColor)

            showTopLine = getBoolean(R.styleable.SuperBar_showTopLine, false)
            showBottomLine = getBoolean(R.styleable.SuperBar_showBottomLine, true)

            showLeftIcon = getBoolean(R.styleable.SuperBar_showLeftIcon, true)
            showRightIcon = getBoolean(R.styleable.SuperBar_showRightIcon, true)
            recycle()
        }

        drawSuperBar()
    }

    private fun drawSuperBar() {
        mIvLeft.setImageResource(leftIcon)
        mIvLeft.apply { if (showLeftIcon) visible() else gone() }

        mIvRight.setImageResource(rightIcon)
        mIvRight.apply { if (showRightIcon) visible() else gone() }

        mTvTitle.text = leftText
        mTvTitle.textSize = leftTextSize
        mTvTitle.setTextColor(leftTextColor)

        mTvContent.text = rightText
        mTvContent.textSize = rightTextSize
        mTvContent.setTextColor(rightTextColor)

        mTopLine.apply { if (showTopLine) visible() else gone() }
        mBottomLine.apply { if (showBottomLine) visible() else gone() }
    }
}