package com.helper.ext


/**
 * @Author smart_yq
 * @Date  17:56
 */

inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit, nullAction: () -> Unit) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}
