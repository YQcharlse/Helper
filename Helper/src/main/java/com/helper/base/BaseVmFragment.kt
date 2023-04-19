package com.helper.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.drake.statelayout.StateLayout
import com.drake.statelayout.Status
import com.drake.statelayout.stateCreate
import com.helper.R
import com.helper.ext.*
import com.helper.net.LoadStatusEntity
import com.helper.net.LoadingDialogEntity
import com.helper.net.LoadingType


/**
 * @Author smart_yq
 * @Date 2021-11-03
 * 描述　:
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment(), BaseIView {

    abstract val layoutId: Int

    private var dataBindView: View? = null

    //是否第一次加载
    private var isFirst: Boolean = true

    //当前Fragment绑定的泛型类ViewModel
    lateinit var mViewModel: VM

    //父类activity
    lateinit var mActivity: AppCompatActivity

    //界面管理
    lateinit var uiStatus: StateLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isFirst = true
        javaClass.simpleName.logD()
        dataBindView = initViewDataBind(inflater, container)
        val rootView = if (dataBindView == null) {
            inflater.inflate(layoutId, container, false)
        } else {
            dataBindView
        }
        rootView!!.post {
            uiStatus = rootView.stateCreate()
            uiStatus.clickNoRepeat {
                onLoadRetry()
            }
        }
        return rootView
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = createViewModel()
        addLoadingUiChange(mViewModel)
        //view加载完成后执行
        initView(savedInstanceState)
        onBindViewClick()
        initObserver()
        onRequestSuccess()
    }


    /**
     * 初始化view操作  这个方法会有延迟，因为使用了LoadSir，需要等待LoadSir注册完成后才能执行
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    open fun lazyLoadData() {}

    /**
     * 创建观察者
     */
    open fun initObserver() {}

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            view?.post {
                lazyLoadData()
                isFirst = false
            }
        }
    }

    /**
     * 点击事件方法 配合setOnclick()拓展函数调用，做到黄油刀类似的点击事件
     */
    open fun onBindViewClick() {}

    /**
     * 注册 UI 事件 监听请求时的回调UI的操作
     */
    fun addLoadingUiChange(viewModel: BaseViewModel) {
        viewModel.loadingChange.run {
            loading.observe(viewLifecycleOwner) {
                when (it.loadingType) {
                    //通用弹窗Dialog
                    LoadingType.LOADING_DIALOG -> {
                        if (it.isShow) {
                            showLoading(it)
                        } else {
                            dismissLoading(it)
                        }
                    }
                    //不同的请求自定义loading
                    LoadingType.LOADING_CUSTOM -> {
                        if (it.isShow) {
                            showCustomLoading(it)
                        } else {
                            dismissCustomLoading(it)
                        }
                    }
                }
            }
            //当分页列表数据第一页返回空数据时 显示空布局
            showEmpty.observe(viewLifecycleOwner) {
                onRequestEmpty(it)
            }
            //当请求失败时
            showError.observe(viewLifecycleOwner) {
                onRequestError(it)
            }
            showSuccess.observe(viewLifecycleOwner) {
                if (uiStatus.status == Status.LOADING) {
                    showSuccessUi()
                }
            }
        }
    }


    /**
     * 请求列表数据为空时 回调
     * @param loadStatus LoadStatusEntity
     */
    override fun onRequestEmpty(loadStatus: LoadStatusEntity) {
        showEmptyUi()
    }


    /**
     * 请求接口失败回调，如果界面有请求接口，需要处理错误业务，请实现它 如果不实现那么 默认吐司错误消息
     * @param loadStatus LoadStatusEntity
     */
    override fun onRequestError(loadStatus: LoadStatusEntity) {
        loadStatus.errorMessage.toast()
    }

    /**
     * 请求成功的回调放在这里面 没干啥就是取了个名字，到时候好找
     */
    override fun onRequestSuccess() {

    }


    /**
     * 空界面，错误界面 点击重试时触发的方法，如果有使用 状态布局的话，一般子类都要实现
     */
    override fun onLoadRetry() {}

    /**
     * 显示 成功状态界面
     */
    override fun showSuccessUi() {
        uiStatus.showContent()
    }

    /**
     * 显示 空数据 状态界面
     */
    override fun showEmptyUi(message: String) {
        uiStatus.showEmpty().apply {
            if (message.isNotBlank()) {
                uiStatus.findViewById<TextView>(R.id.tv_empty_text).text = message
            }
        }
    }

    /**
     * 显示自定义loading 在请求时 设置 loadingType类型为LOADING_CUSTOM 时才有效 可以根据setting中的requestCode判断
     * 具体是哪个请求显示该请求自定义的loading
     * @param setting LoadingDialogEntity
     */
    override fun showCustomLoading(setting: LoadingDialogEntity) {
        showLoadingExt(setting.loadingMessage)
    }

    /**
     * 隐藏自定义loading 在请求时 设置 loadingType类型为LOADING_CUSTOM 时才有效 可以根据setting中的requestCode判断
     * 具体是哪个请求隐藏该请求自定义的loading
     * @param setting LoadingDialogEntity
     */
    override fun dismissCustomLoading(setting: LoadingDialogEntity) {
        dismissLoadingExt()
    }

    override fun showLoading(setting: LoadingDialogEntity) {
        showLoadingExt(setting.loadingMessage)
    }

    override fun dismissLoading(setting: LoadingDialogEntity) {
        dismissLoadingExt()
    }


    /**
     * 供子类BaseVmDbActivity 初始化 DataBinding ViewBinding操作
     */
    open fun initViewDataBind(inflater: LayoutInflater, container: ViewGroup?): View? {
        return null
    }
}