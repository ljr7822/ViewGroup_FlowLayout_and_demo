package com.example.myviewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * author : Iwen大大怪
 * date   : 2020/10/2411:18
 */
class MyViewGroupAdapt : ViewGroup {
    private val space = 30
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 测量自己 size mode  -> 提供给子控件进行测量自己的（限制条件）
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 获取父容器的size
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)

        // 确定子控件的尺寸
        var childWidth = 0
        var childHeight = 0

        if (childCount == 1) {
            childWidth = parentWidth - 2 * space
            childHeight = parentHeight - 2 * space
        } else {
            childWidth = (parentWidth - 3 * space) / 2
            // 计算有多少行
            val row = (childCount + 1) / 2
            childHeight = (parentHeight - (row + 1) * space) / row
        }

        // 将尺寸设置给子控件
        // 先确定限制条件 MeasureSpec EXACTLY AT_MOST UNSPECIFIC
        val wspec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY)
        val hspec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(wspec, hspec)
        }

        // 设置容器尺寸
        setMeasuredDimension(200, 200);
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // 对子控件进行布局
        var left = 0
        var top = 0
        var right = 0
        var bottom = 0
        for (i in 0 until childCount) {
            // 确定i具体的位置 row column
            val row = i / 2
            val column = i % 2

            val child = getChildAt(i)
            left = space + column * (child.measuredWidth + space)
            top = space + row * (child.measuredHeight + space)
            right = left + child.measuredWidth
            bottom = top + child.measuredHeight
            child.layout(left, top, right, bottom)
        }
    }
}