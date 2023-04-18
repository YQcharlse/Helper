package com.helper.base

import android.view.View
import com.helper.net.LoadStatusEntity
import com.helper.net.LoadingDialogEntity

/**
 * @Author smart_yq
 * @Date 2021-11-03
 * 描述　:
 */
interface BaseIView {

    /**
     * 子类可传入自己的标题栏 不给默认是null
     * @return View?
     */
    fun getTitleBarView(): View? {
        return null
    }

    /**
     * 请求成功
     */
    fun onRequestSuccess()

    /**
     * 请求失败
     * @param loadStatus LoadStatusEntity
     */
    fun onRequestError(loadStatus: LoadStatusEntity)

    /**
     * 显示通用loading弹窗dialog
     */
    fun showLoading(setting: LoadingDialogEntity)

    /**
     * 隐藏通用loading弹窗dialog
     */
    fun dismissLoading(setting: LoadingDialogEntity)

    /**
     * 显示自定义loading弹窗dialog
     */
    fun showCustomLoading(setting: LoadingDialogEntity)

    /**
     * 隐藏自定义loading弹窗dialog
     */
    fun dismissCustomLoading(setting: LoadingDialogEntity)


}