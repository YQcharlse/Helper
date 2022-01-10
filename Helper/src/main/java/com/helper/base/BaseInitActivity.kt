package com.helper.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author smart_yq
 * @Date 2021-11-03
 * 描述　:
 */
abstract class BaseInitActivity : AppCompatActivity() {

    abstract val layoutId: Int

    var dataBindView :View? = null

}