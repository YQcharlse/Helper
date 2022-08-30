package com.helper.base

import android.app.Application
import android.view.Gravity
import com.helper.ext.dp
import com.helper.state.BaseEmptyCallback
import com.helper.state.BaseErrorCallback
import com.helper.state.BaseLoadingCallback
import com.helper.util.KtxActivityLifecycleCallbacks
import com.helper.util.SmartGenHelperLog
import com.hjq.toast.ToastUtils
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir

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

        //注册界面状态管理
        LoadSir.beginBuilder()
            .addCallback(BaseErrorCallback())
            .addCallback(BaseEmptyCallback())
            .addCallback(BaseLoadingCallback())
            .setDefaultCallback(SuccessCallback::class.java)
            .commit()

        //初始化吐司 这个吐司必须要主线程中初始化
        ToastUtils.init(app)
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 100.dp)
    }

}