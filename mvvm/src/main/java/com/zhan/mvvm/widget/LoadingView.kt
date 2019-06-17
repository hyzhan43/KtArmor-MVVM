package com.zhan.mvvm.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.zhan.mvvm.R


/**
 *  @author: hyzhan
 *  @date:   2019/5/23
 *  @desc:   TODO
 */
class LoadingView
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyle: Int = 0) : View(context, attrs, defStyle) {

    // 外圆画笔
    private var outerPaint: Paint
    // 内圆画笔
    private var innerPaint: Paint

    private var innerRectF: RectF

    private var outerRectF: RectF

    // 默认2000
    var duration = 2000
        set(value) {
            field = value
            valueAnimator.duration = value.toLong()
        }
    //内外层圆弧的颜色，默认都为黑色
    var outerColor = Color.BLACK
        set(value) {
            field = value
            outerPaint = initPaint(value)
        }

    var innerColor = Color.BLACK
        set(value) {
            field = value
            innerPaint = initPaint(value)
        }

    // 内外弧间隔
    var intervalWidth = 30f
        set(value) {
            field = value
            innerRectF = initInnerRectF()
        }

    // loading view 默认size
    private var defaultSize = 200
        set(value) {
            field = value
            innerRectF = initInnerRectF()
            outerRectF = initOuterRectF()
        }

    //圆弧的宽度
    var arcWidth = 15f
        set(value) {
            field = value
            outerPaint = initPaint(outerColor)
            innerPaint = initPaint(innerColor)
            innerRectF = initInnerRectF()
            outerRectF = initOuterRectF()
        }

    //外层圆弧转过的角度,
    private var outerArcAngle = 300f
    private var innerArcAngle = 60f
    //计算改变之后的起始角度
    var startAngle = 105f

    private val valueAnimator by lazy { ValueAnimator.ofFloat(0f, 1f) }

    init {
        //取出自定义的属性并赋值
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView, defStyle, 0)

        with(typeArray){
            duration = getInt(R.styleable.LoadingView_duration, duration)

            outerColor = getColor(R.styleable.LoadingView_outer_color, Color.BLACK)
            innerColor = getColor(R.styleable.LoadingView_inner_color, Color.BLACK)

            startAngle = getFloat(R.styleable.LoadingView_start_angle, startAngle)

            arcWidth = getDimension(R.styleable.LoadingView_arc_width, arcWidth)
            intervalWidth = getDimension(R.styleable.LoadingView_interval_width, intervalWidth)

            recycle()
        }

        outerPaint = initPaint(outerColor)
        innerPaint = initPaint(innerColor)

        outerRectF = initOuterRectF()
        innerRectF = initInnerRectF()

        initValueAnimator()
    }

    private fun initPaint(color: Int): Paint {

        val paint = Paint()

        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = arcWidth
        // 设置拐角的形状
        paint.strokeJoin = Paint.Join.ROUND
        // 设置线头的形状
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = color

        return paint
    }

    private fun initOuterRectF(): RectF {
        val leftTop = arcWidth
        val rightBottom = defaultSize - arcWidth

        return RectF().also { it.set(leftTop, leftTop, rightBottom, rightBottom) }
    }

    private fun initInnerRectF(): RectF {
        val leftTop = intervalWidth + arcWidth
        val rightBottom = defaultSize - intervalWidth - arcWidth

        return RectF().also { it.set(leftTop, leftTop, rightBottom, rightBottom) }
    }

    private fun initValueAnimator() {
        valueAnimator.duration = duration.toLong()
        valueAnimator.interpolator = LinearInterpolator()
        //无限循环
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.repeatMode = ValueAnimator.RESTART

        valueAnimator.addUpdateListener { valueAnimator ->
            val curValue = valueAnimator.animatedValue as Float
            startAngle = 360 * curValue
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measureSize(widthMeasureSpec, defaultSize)
        val height = measureSize(heightMeasureSpec, defaultSize)

        defaultSize = Math.min(width, height)
        setMeasuredDimension(defaultSize, defaultSize)
    }

    //根据不同的model处理不同的尺寸
    private fun measureSize(measureSpec: Int, defaultSize: Int): Int {
        var result = 0
        val model = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        when (model) {
            //不限制，使用默认的尺寸
            MeasureSpec.UNSPECIFIED -> result = defaultSize
            // 最大上限
            MeasureSpec.AT_MOST -> result = Math.min(defaultSize, size)
            MeasureSpec.EXACTLY -> result = size
        }

        return result
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 画外弧
        canvas?.drawArc(outerRectF, startAngle, outerArcAngle, false, outerPaint)

        // 画内弧
        canvas?.drawArc(innerRectF, 360 - startAngle, innerArcAngle, false, innerPaint)
    }

    fun start() {
        if (!valueAnimator.isRunning) {
            valueAnimator.start()
        }
    }

    fun stop() {
        if (valueAnimator.isRunning) {
            valueAnimator.cancel()
        }
    }
}