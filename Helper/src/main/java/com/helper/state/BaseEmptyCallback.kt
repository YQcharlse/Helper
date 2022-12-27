package com.helper.state

import com.helper.R
import com.kingja.loadsir.callback.Callback

/**
 * Author   smart_yq
 * Date     2021-11-03
 * 描述     空界面
 */
class BaseEmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}