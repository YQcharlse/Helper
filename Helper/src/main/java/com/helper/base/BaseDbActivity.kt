package com.helper.base

import android.view.View
import androidx.databinding.ViewDataBinding
import com.helper.ext.inflateBinding
import com.noober.background.BackgroundLibrary

/**
 * @Author smart_yq
 * @Date 2021-11-03
 * 描述　:
 */
abstract class BaseDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>(),
    BaseIView {

    //使用了DataBinding 就不需要 layoutId了，因为 会从DB泛型 找到相关的view
    override val layoutId: Int = 0

    lateinit var mBind: DB

    /**
     * 创建DataBinding
     */
    override fun initViewDataBind(): View? {
        //利用反射 根据泛型得到 ViewDataBinding
        mBind = inflateBinding()
        BackgroundLibrary.inject(this)
        mBind.lifecycleOwner = this
        return mBind.root
    }
}