package com.smartgenhelpter.app

import com.effective.android.anchors.task.Task
import com.effective.android.anchors.task.TaskCreator
import com.effective.android.anchors.task.project.Project
import com.helper.base.appContext
import com.hjq.language.MultiLanguages
import com.smartgenhelpter.app.api.NetHttpClient
import com.tencent.mmkv.MMKV
import rxhttp.RxHttpPlugins


/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述 :
 */
class AppTaskFactory : Project.TaskFactory(Creator)

object Creator : TaskCreator {
    override fun createTask(taskName: String): Task {
        return when (taskName) {
            InitNetWork.TASK_ID -> InitNetWork()
            InitComm.TASK_ID -> InitComm()
            InitUtils.TASK_ID -> InitUtils()
            InitToast.TASK_ID -> InitToast()
            else -> InitDefault()
        }
    }
}

class InitDefault : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "0"
    }

    override fun run(name: String) {

    }
}


/**
 * 初始化网络
 */
class InitNetWork : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "1"
    }

    override fun run(name: String) {
        //传入自己的OKHttpClient 并添加了自己的拦截器
        RxHttpPlugins.init(NetHttpClient.getDefaultOkHttpClient().build())
    }
}


//初始化常用控件类
class InitComm : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "2"
    }

    override fun run(name: String) {

    }
}

//初始化Utils
class InitUtils : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "3"
    }

    override fun run(name: String) {
        //初始化Log打印
        MMKV.initialize(appContext)

        // 初始化语种切换框架
        MultiLanguages.init(appContext)


    }
}

//初始化Utils
class InitToast : Task(TASK_ID, false) {
    companion object {
        const val TASK_ID = "4"
    }

    override fun run(name: String) {

    }
}




