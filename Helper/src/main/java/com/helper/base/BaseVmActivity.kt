package com.helper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.drake.statelayout.StateLayout
import com.drake.statelayout.Status
import com.gyf.immersionbar.ImmersionBar
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
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity(), BaseIView {

    abstract val layoutId: Int

    private var dataBindView: View? = null

    //当前Activity绑定的 ViewModel
    lateinit var mViewModel: VM

    //toolbar 这个可替换成自己想要的标题栏
    private var mTitleBarView: View? = null

    lateinit var uiStatus: StateLayout

    /** 状态栏沉浸  */
    private var mImmersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        overridePendingTransition(R.anim.right_in_activity, R.anim.right_out_activity)
        //生成ViewModel
        mViewModel = createViewModel()
        javaClass.simpleName.logD()
        addLoadingUiChange(mViewModel)
        //初始化 status View
        initStatusView()
        //注册界面响应事件
        initView(savedInstanceState)
        //初始化请求成功方法
        onRequestSuccess()
        //初始化绑定点击方法
        onBindViewClick()
    }

    private fun initStatusView() {
        mTitleBarView = getTitleBarView()
        dataBindView = initViewDataBind()
        mTitleBarView?.let {
            findViewById<LinearLayout>(R.id.baseRootView).addView(it, 0)
            //是否隐藏标题栏
            it.visibleOrGone(showToolBar())
        }
        initImmersionBar()
        uiStatus = findViewById(R.id.baseStateView)
        uiStatus.clickNoRepeat {
            onLoadRetry()
        }
        findViewById<FrameLayout>(R.id.baseContentView).addView(
            if (dataBindView == null) LayoutInflater.from(this).inflate(layoutId, null) else dataBindView
        )
    }

    /**
     * 初始化view 这个方法会有延迟，因为使用了LoadSir，需要等待LoadSir注册完成后才能执行
     */
    abstract fun initView(savedInstanceState: Bundle?)


    /**
     * 是否隐藏 标题栏 默认显示
     */
    open fun showToolBar(): Boolean {
        return true
    }


    /**
     * 是否状态栏 沉浸
     */
    open fun isStatusBarEnabled(): Boolean {
        return true
    }


    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected open fun initImmersionBar() {
        //设置共同沉浸式样式
        mTitleBarView?.let {
            if (showToolBar()) {
                getStatusBarConfig().titleBar(it).init()
            }
        }
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    open fun getStatusBarConfig(): ImmersionBar {
        if (mImmersionBar == null) {
            mImmersionBar = createStatusBarConfig()
        }
        return mImmersionBar as ImmersionBar
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected open fun createStatusBarConfig(): ImmersionBar {
        return ImmersionBar.with(this)
            // 指定导航栏背景颜色
            .navigationBarColor(R.color.helperColorWhite)
            .autoDarkModeEnable(true, 0.2f)
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
            loading.observe(this@BaseVmActivity) {
                when (it.loadingType) {
                    LoadingType.LOADING_DIALOG -> {
                        if (it.isShow) {
                            showLoading(it)
                        } else {
                            dismissLoading(it)
                        }
                    }
                    LoadingType.LOADING_CUSTOM -> {
                        if (it.isShow) {
                            showCustomLoading(it)
                        } else {
                            dismissCustomLoading(it)
                        }
                    }
                }
            }
            showEmpty.observe(this@BaseVmActivity) {
                onRequestEmpty(it)
            }
            showError.observe(this@BaseVmActivity) {
                onRequestError(it)
            }
            showSuccess.observe(this@BaseVmActivity){
                if (uiStatus.status == Status.LOADING){
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

    override fun showSuccessUi() {
        uiStatus.showContent()
    }

    override fun showEmptyUi(message: String) {
        uiStatus.showEmpty().apply {
            if (message.isNotBlank()) {
                findViewById<TextView>(R.id.tv_empty_text).text = message
            }
        }
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
    override fun onRequestSuccess() {}


    /**
     * 空界面，错误界面 点击重试时触发的方法，如果有使用 状态布局的话，一般子类都要实现
     */
    override fun onLoadRetry() {}

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

    override fun finish() {
        dismissLoadingExt()
        super.finish()
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity)
    }


    /**
     * 供子类BaseVmDbActivity 初始化 DataBinding ViewBinding操作
     */
    open fun initViewDataBind(): View? {
        return null
    }
}