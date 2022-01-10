package com.helper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import java.lang.reflect.ParameterizedType

/**
 * @Author smart_yq
 * @Date 2021-11-03
 * 描述　:
 */
abstract class BaseDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>(),
    BaseIView {

    //使用了DataBinding 就不需要 layoutId了，因为 会从 DB泛型 找到相关的view
    override val layoutId: Int = 0

    lateinit var mDataBind: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDataBind(inflater, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 创建 DataBinding
     */
    private fun initDataBind(inflater: LayoutInflater, container: ViewGroup?) {
        //利用反射 根据泛型得到 ViewDataBinding
        val superClass = javaClass.genericSuperclass
        val aClass = (superClass as ParameterizedType).actualTypeArguments[1] as Class<*>
        val method = aClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        mDataBind = method.invoke(null, inflater, container, false) as DB
        //如果重新加载，需要清空之前的view，不然会报错
        (dataBindView?.parent as? ViewGroup)?.removeView(dataBindView)
        dataBindView = mDataBind.root
        mDataBind.lifecycleOwner = this
    }
}