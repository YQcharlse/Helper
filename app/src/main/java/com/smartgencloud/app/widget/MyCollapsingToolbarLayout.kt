package com.smartgencloud.app.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.CollapsingToolbarLayout


/**
 * @Author smart_yq
 * @Date 2021-11-16
 * 描述 :
 */
class MyCollapsingToolbarLayout : CollapsingToolbarLayout {

    /** 渐变监听  */
    private var mListener: OnScrimsListener? = null

    /** 当前渐变状态  */
    private var mScrimsShown = false


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setScrimsShown(shown: Boolean, animate: Boolean) {
        super.setScrimsShown(shown, true)
        // 判断渐变状态是否改变了
        if (mScrimsShown == shown) {
            return
        }
        // 如果是就记录并且回调监听器
        mScrimsShown = shown
        if (mListener == null) {
            return
        }
        mListener!!.onScrimsStateChange(this, mScrimsShown)
    }

    /**
     * 获取当前的渐变状态
     */
    fun isScrimsShown(): Boolean {
        return mScrimsShown
    }

    /**
     * 设置CollapsingToolbarLayout渐变监听
     */
    fun setOnScrimsListener(listener: OnScrimsListener) {
        mListener = listener
    }

    /**
     * CollapsingToolbarLayout渐变监听器
     */
    interface OnScrimsListener {
        /**
         * 渐变状态变化
         *
         * @param shown         渐变开关
         */
        fun onScrimsStateChange(
            layout: MyCollapsingToolbarLayout,
            shown: Boolean
        )
    }
}
