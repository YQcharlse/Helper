package com.helper.net


/**
 * @Author smart_yq
 * @Date 2021-11-03
 */
data class LoadingDialogEntity(
    @LoadingType var loadingType: Int = LoadingType.LOADING_NULL,
    var loadingMessage: String = "",
    var isShow: Boolean = false,
    var requestCode: String = "mmp"
)