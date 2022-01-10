package com.helper.state

import com.helper.R
import com.kingja.loadsir.callback.Callback

/**
 * @Author smart_yq
 * @Date 2021-11-03
 * 描述　:
 */
class BaseErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}