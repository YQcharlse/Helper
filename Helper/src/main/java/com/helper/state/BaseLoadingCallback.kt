package com.helper.state

import android.content.Context
import android.view.View
import com.helper.R
import com.kingja.loadsir.callback.Callback

/**
 * @Author smart_yq
 * @Date 2021-11-03
 * 描述　:
 */
class BaseLoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    /**
     * 是否是 点击不可重试
     */
    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}