package com.helper.base

import android.app.Application
import com.helper.util.KtxActivityLifecycleCallbacks
import com.helper.util.SmartGenHelperLog

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
    }

}