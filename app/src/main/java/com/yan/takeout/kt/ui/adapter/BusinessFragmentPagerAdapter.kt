package com.yan.takeout.kt.ui.adapter

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentPagerAdapter

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 16:38
 *  @description : 详情页ViewPager的adapter
 */
class BusinessFragmentPagerAdapter(fm: FragmentManager?, val fragments: List<Fragment>, val titles: List<String>) :
        FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = titles.size
}