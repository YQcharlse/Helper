package com.smartgencloud.app.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * @Author smart_yq
 * @Date 2021-11-22
 * 描述 : 自定义字体
 */
class OppTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    /**
     * 重写设置字体方法
     */
    override fun setTypeface(tf: Typeface?) {
        var tf = tf
        tf = Typeface.createFromAsset(context.assets, "fonts/OPPOSans-H.ttf")
        super.setTypeface(tf)
    }
}