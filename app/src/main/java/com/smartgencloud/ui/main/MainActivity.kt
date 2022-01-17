package com.smartgencloud.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helper.ext.finishAllActivity
import com.helper.ext.getStringExt
import com.helper.ext.toast
import com.smartgencloud.R
import com.smartgencloud.app.base.BaseActivity
import com.smartgencloud.app.ext.mmkv
import com.smartgencloud.data.annotation.ValueKey
import com.smartgencloud.databinding.ActivityMainBinding
import com.smartgencloud.ui.main.viewmodel.MainViewModel

/**
 * @Author: smartgen_yq
 * @Data: 2022-01-08
 * 描述:主页
 */

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {


    //Tab ids
    private var ids = mutableListOf(
        R.id.navigationFrontPage,
        R.id.navigationProgram,
        R.id.navigationProduct,
        R.id.navigationUser
    ).toMutableList()

    override fun showToolBar(): Boolean = false


    override fun initView(savedInstanceState: Bundle?) {

        mBind.bnMain.itemIconTintList = null

        val menu = mBind.bnMain.menu
        menu.getItem(2).isVisible = !mmkv.decodeString(ValueKey.userToken).isNullOrEmpty()

        clearToast(mBind.bnMain, ids)


        mBind.vpMain.adapter = MainAdapter(this)
        mBind.vpMain.offscreenPageLimit = mBind.vpMain.adapter!!.itemCount
        mBind.vpMain.isUserInputEnabled = false

        mBind.bnMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigationFrontPage -> {
                    mBind.vpMain.setCurrentItem(0, false)
                }
                R.id.navigationProgram -> {
                    mBind.vpMain.setCurrentItem(1, false)
                }
                R.id.navigationProduct -> {
                    mBind.vpMain.setCurrentItem(2, false)
                }
                R.id.navigationUser -> {
                    mBind.vpMain.setCurrentItem(3, false)
                }
            }
            true
        }


    }


    private var mIsExit = false


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                finishAllActivity()
            } else {
                getStringExt(R.string.again_exit).toast()
                mIsExit = true
                Handler(Looper.getMainLooper()).postDelayed({ mIsExit = false }, 2000)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /*
     * 清除长按时的toast
    * @param bottomNavigationView 当前BottomNavigationView
    * @param ids                  与配置文件中对应的所有id
    */
    private fun clearToast(bottomNavigationView: BottomNavigationView, ids: MutableList<Int>) {
        var bottomNavigationMenuView: ViewGroup = (bottomNavigationView.getChildAt(0) as ViewGroup)
        //遍历子View,重写长按点击事件
        for (position in 0 until ids.size) {
            bottomNavigationMenuView.getChildAt(position)
                .findViewById<View>((ids[position] as Int?)!!)
                .setOnLongClickListener { true }
        }

    }

}