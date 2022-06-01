package com.smartgenhelpter.app.ext

import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * @Author smart_yq
 * @Date 2022-05-26
 * 描述 : 全局发送消息 通过LiveData实现
 */
object LiveDataEvent {

    //示例：登录成功发送通知
    val loginEvent = UnPeekLiveData<Boolean>()
}