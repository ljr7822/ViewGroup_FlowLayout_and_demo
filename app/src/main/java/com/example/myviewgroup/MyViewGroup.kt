package com.example.myviewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * author : Iwen大大怪
 * create : 2020/10/24 9:34
 */
class MyViewGroup : ViewGroup {
    private val space = 30

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 测量自己 size mode  -> 提供给子控件进行测量自己的（限制条件）
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 获取子控件的属性
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            // 测量子控件
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }
        // 确定父容器的尺寸
        var w = 0
        var h = 0
        val row = (childCount - 1) / 2
        var column = (childCount - 1) % 2
        val child = getChildAt(0)
        if (childCount == 1) {
            w = child.measuredWidth + 2 * space
        } else {
            w = 2 * child.measuredWidth + 3 * space
        }
        h = space + (row + 1) * (child.measuredHeight + space)
        // 设置父容器的尺寸
        setMeasuredDimension(w, h)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val child = getChildAt(0)
        // 对子控件进行布局
        var left = 0
        var top = 0
        var right = 0
        var bottom = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val row = i / 2
            val column = i % 2
            left = space + column * (child.measuredWidth + space)
            top = space + row * (child.measuredHeight + space)
            right = left + child.measuredWidth
            bottom = top + child.measuredHeight
            child.layout(left, top, right, bottom)
        }
    }
}