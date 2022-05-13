package com.smartgenhelpter.app.ext

import com.smartgenhelpter.data.annotation.ValueKey
import com.tencent.mmkv.MMKV

/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述　:
 */

/**
 * 获取MMKV
 */
val mmkv: MMKV by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    MMKV.mmkvWithID(ValueKey.MMKV_APP_KEY)
}


