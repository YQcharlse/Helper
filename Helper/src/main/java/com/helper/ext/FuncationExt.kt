package com.helper.ext

import androidx.lifecycle.MutableLiveData


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


inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit) {
    if (this != null) {
        notNullAction.invoke(this)
    }
}

inline var <reified T> MutableLiveData<T>.postValue: T?
    get() {
        return this.value
    }
    set(value) {
        this.postValue(value)
    }
