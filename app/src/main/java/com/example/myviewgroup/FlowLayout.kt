package com.example.myviewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * author : Iwen大大怪
 * create : 2020/10/24 14:38
 */
class FlowLayout : ViewGroup {

    private val space = 30
    constructor(context:Context):super(context)
    constructor(context:Context,attrs:AttributeSet?):super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 测量自己 size mode  -> 提供给子控件进行测量自己的（限制条件）
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 定义变量记录容器的最终尺寸
        var resultWidth = 0
        var resultHeight = 0

        // 获取子控件
        val child = getChildAt(0)
        measureChild(child,widthMeasureSpec,heightMeasureSpec)


        // 获取容器本身的尺寸和mode
        val parentWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val parentWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        //先确定宽度 exactly at_most unspecific
        resultWidth = when(parentWidthMode){
            MeasureSpec.EXACTLY-> parentWidthSize
            MeasureSpec.AT_MOST-> child.measuredWidth
            else-> parentWidthSize
        }
        // 获取容器本身的高度和mode
        val parentHeightSize = MeasureSpec.getSize(heightMeasureSpec)
        val parentHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        resultHeight = when(parentHeightMode){
            MeasureSpec.EXACTLY-> parentHeightSize
            MeasureSpec.AT_MOST-> child.measuredHeight
            else-> parentHeightSize
        }
        // 设置容器的尺寸
        setMeasuredDimension(resultWidth,resultHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //对子控件进行布局
        val child = getChildAt(0)

        val left = 0
        val top = 0
        val right = 0 + child.measuredWidth
        val bottom = 0 + child.measuredHeight
        child.layout(left,top,right,bottom)
    }
}