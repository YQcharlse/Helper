package com.smartgenhelpter.app.base

import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ktx.immersionBar
import com.helper.base.BaseDbFragment
import com.helper.base.BaseViewModel

/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述 : 使用DataBinding 需要自定义修改什么就重写什么 具体方法可以 搜索 BaseIView 查看
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseDbFragment<VM, DB>() {


    //需要自定义修改什么就重写什么 具体方法可以 搜索 BaseIView 查看
    override fun onResume() {
        super.onResume()
        immersionBar {
            statusBarDarkFont(true)
        }
    }
}