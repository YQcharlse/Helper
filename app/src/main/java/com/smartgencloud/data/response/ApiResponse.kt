package com.smartgencloud.data.response

/**
 * 描述　: 数据基类
 */
data class ApiResponse<T>(
    var data: T,
    var code: Int = -1,
    var msg: String = ""
)