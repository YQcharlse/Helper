package com.smartgencloud.app.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.helper.ext.dp
import com.helper.ext.dp2px
import com.helper.ext.getColorExt
import com.helper.ext.getDrawableExt
import com.hjq.bar.TitleBar
import com.smartgencloud.R

/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述 :
 */
class CustomToolBar : FrameLayout {

    private lateinit var toolBar: TitleBar

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val view = LayoutInflater.from(context).inflate(R.layout.toolbar_layout_custom, this)
        toolBar = view.findViewById(R.id.toolBar)
        toolBar.setTitleColor(getColorExt(R.color.black_121))
        toolBar.setTitleSize(17F)
        toolBar.setTitleStyle(Typeface.BOLD)

        toolBar.setRightTitleColor(getColorExt(R.color.black_121))
        toolBar.setRightTitleSize(14F)
        toolBar.setRightIconPadding(5.dp)

        toolBar.setLineSize(dp2px(0.5F))
        toolBar.setLineColor(getColorExt(R.color.grey_1a6))
    }

    fun setCenterTitle(titleStr: String?) {
        toolBar.title = titleStr
    }

    fun setLeftIcon(leftInt: Int?) {
        toolBar.leftIcon = leftInt?.let { getDrawableExt(it) }
    }

    fun setRightIcon(rightInt: Int?) {
        toolBar.rightIcon = rightInt?.let { getDrawableExt(it) }
    }

    fun setRightTitle(titleStr: String?) {
        toolBar.rightTitle = titleStr
    }

    fun setCenterTitleColor(colorResId: Int) {
        toolBar.setTitleColor(colorResId)
    }

    fun setToolbarBackGround(colorResId: Int) {
        toolBar.setBackgroundColor(colorResId)
    }



    fun setLineVisible(isVisible: Boolean?) {
        toolBar.setLineVisible(isVisible!!)
    }

    fun getBaseToolBar(): TitleBar {
        return toolBar
    }
}