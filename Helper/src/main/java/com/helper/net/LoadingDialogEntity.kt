package com.helper.net

import kotlinx.coroutines.CoroutineScope


/**
 * Author smart_yq
 * Date 2021-11-03
 */
data class LoadingDialogEntity(
    @LoadingType var loadingType: Int = LoadingType.LOADING_NULL,
    var loadingMessage: String = "",
    var isShow: Boolean = false,
    var requestCode: String = "mmp",
    var coroutineScope: CoroutineScope? = null //请求绑定的作用域
)