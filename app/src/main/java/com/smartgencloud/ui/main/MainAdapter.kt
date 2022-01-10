package com.smartgencloud.ui.main

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smartgencloud.ui.main.fragment.DeviceFragment
import com.smartgencloud.ui.main.fragment.FrontPageFragment
import com.smartgencloud.ui.main.fragment.NetFragment
import com.smartgencloud.ui.main.fragment.UserFragment

/**
 * @Author smart_yq
 * @Date 2022-01-08
 * 描述 :
 */
class MainAdapter(fa: FragmentActivity) :
    FragmentStateAdapter(fa) {


    companion object {
        const val PAGE_ONE = 0
        const val PAGE_TWO = 1
        const val PAGE_THREE = 2
        const val PAGE_FOUR = 3
    }


    private val fragments: SparseArray<Fragment> = SparseArray()

    init {
        fragments.put(PAGE_ONE, FrontPageFragment())
        fragments.put(PAGE_TWO, DeviceFragment())
        fragments.put(PAGE_THREE, NetFragment())
        fragments.put(PAGE_FOUR, UserFragment())

    }

    override fun getItemCount(): Int {
        return fragments.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }


}