package com.smartgencloud.app

import android.app.Application
import android.content.Context
import com.effective.android.anchors.AnchorsManager
import com.effective.android.anchors.task.project.Project
import com.helper.base.SmartGenHelper
import com.helper.ext.currentProcessName
import com.hjq.language.MultiLanguages
import com.smartgencloud.BuildConfig


/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述 :
 */
class Application : Application() {

    override fun attachBaseContext(base: Context) {
        // 绑定语种
        super.attachBaseContext(MultiLanguages.attach(base))
    }

    override fun onCreate() {
        super.onCreate()
        SmartGenHelper.init(this, BuildConfig.DEBUG)
        val processName = currentProcessName
        if (processName == packageName) {
            // 主进程初始化
            onMainProcessInit()
        } else {
            // 其他进程初始化
            processName?.let { onOtherProcessInit(it) }
        }
    }


    /**
     * @description  代码的初始化请不要放在onCreate直接操作，按照下面新建异步方法
     */
    private fun onMainProcessInit() {
        AnchorsManager.getInstance()
            .debuggable(BuildConfig.DEBUG)
            //设置锚点
            .addAnchor(
                InitNetWork.TASK_ID,
                InitUtils.TASK_ID,
                InitComm.TASK_ID,
                InitToast.TASK_ID
            )
            .start(
                Project.Builder("app", AppTaskFactory())
                    .add(InitNetWork.TASK_ID)
                    .add(InitComm.TASK_ID)
                    .add(InitUtils.TASK_ID)
                    .add(InitToast.TASK_ID)
                    .build()
            )
    }

    /**
     * 其他进程初始化，[processName] 进程名
     */
    private fun onOtherProcessInit(it: String) {


    }


}