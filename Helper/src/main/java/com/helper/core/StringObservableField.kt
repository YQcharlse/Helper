package com.helper.core

import androidx.databinding.ObservableField


class StringObservableField(value: String = "") : ObservableField<String>(value) {

    override fun get(): String {
        return if (super.get().isNullOrEmpty()) "" else super.get()!!
    }

}