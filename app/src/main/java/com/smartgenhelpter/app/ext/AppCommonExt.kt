package com.smartgenhelpter.app.ext

import com.hjq.bar.OnTitleBarListener
import com.hjq.bar.TitleBar
import com.smartgenhelpter.ui.widget.CustomToolBar


/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述　:
 */


/**
 * 初始化toolbar
 */
fun CustomToolBar.initBack(
    titleStr: String? = "",
    rightStr: String? = "",
    leftIcon: Int? = null,
    rightIcon: Int? = null,
    onLeftClick: (toolbar: CustomToolBar) -> Unit,
    onRightClick: (toolbar: CustomToolBar) -> Unit,
    lineVisit: Boolean? = false,
): CustomToolBar {
    this.setCenterTitle(titleStr)
    this.setRightTitle(rightStr)
    this.setLeftIcon(leftIcon)
    this.setRightIcon(rightIcon)
    this.setLineVisible(lineVisit)
    this.getBaseToolBar().setOnTitleBarListener(object : OnTitleBarListener {
        override fun onLeftClick(titleBar: TitleBar) {
            onLeftClick.invoke(this@initBack)
        }

        override fun onRightClick(titleBar: TitleBar) {
            onRightClick.invoke(this@initBack)
        }
    })
    return this
}





