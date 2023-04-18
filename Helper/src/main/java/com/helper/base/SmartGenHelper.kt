package com.helper.base

import android.app.Application
import android.view.Gravity
import com.drake.statelayout.StateConfig
import com.helper.R
import com.helper.ext.dp
import com.helper.util.KtxActivityLifecycleCallbacks
import com.helper.util.SmartGenHelperLog
import com.hjq.toast.Toaster

/**
 * @Author smart_yq
 * @Date 2022-01-18
 * 描述 :
 */
/**
 * 全局上下文，可直接拿
 */
val appContext: Application by lazy { SmartGenHelper.app }

object SmartGenHelper {

    lateinit var app: Application

    /**
     * 框架初始化
     * @param application Application 全局上下文
     * @param debug Boolean  true为debug模式，会打印Log日志 false 关闭Log日志
     */
    fun init(application: Application, debug: Boolean) {
        app = application
        SmartGenHelperLog = debug
        //注册全局 activity生命周期监听
        application.registerActivityLifecycleCallbacks(KtxActivityLifecycleCallbacks())

        StateConfig.apply {
            emptyLayout = R.layout.layout_empty
            setRetryIds(R.id.state_empty_linear)
        }

        //初始化吐司 这个吐司必须要主线程中初始化
        Toaster.init(app)
        Toaster.setGravity(Gravity.BOTTOM, 0, 100.dp)

    }

}