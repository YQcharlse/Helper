package com.smartgenhelpter.app.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.helper.base.BaseDbActivity
import com.helper.base.BaseViewModel
import com.hjq.language.MultiLanguages
import com.smartgenhelpter.R
import com.smartgenhelpter.ui.widget.CustomToolBar


/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述 : 使用DataBinding 需要自定义修改什么就重写什么 具体方法可以 搜索 BaseIView 查看
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseDbActivity<VM, DB>() {

    lateinit var mToolbar: CustomToolBar

    override fun attachBaseContext(newBase: Context?) {
        // 绑定语种
        super.attachBaseContext(MultiLanguages.attach(newBase))
    }


    override fun getTitleBarView(): View? {
        val titleBarView = LayoutInflater.from(this).inflate(R.layout.layout_titlebar_view, null)
        mToolbar = titleBarView.findViewById(R.id.customToolBar)
        return titleBarView
    }

    override fun initImmersionBar() {
        //设置共同沉浸式样式
        if (isStatusBarEnabled()) {
            if (showToolBar()) {
                getStatusBarConfig().titleBar(mToolbar).statusBarDarkFont(true).init()
            } else {
                getStatusBarConfig().statusBarDarkFont(true).init()
            }
        }
    }


}