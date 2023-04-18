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
     * 展示加载中界面
     */
    fun showEmptyUi(message: String = "")


    /**
     * 界面显示加载成功
     */
    fun showSuccessUi()

    /**
     * 请求成功
     */
    fun onRequestSuccess()

    /**
     * 请求数据为空时 在 ResponseParser 中判断了如果是列表数据，是第一页，且没有数据时 回调这个方法
     * @param loadStatus LoadStatusEntity
     */
    fun onRequestEmpty(loadStatus: LoadStatusEntity)

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