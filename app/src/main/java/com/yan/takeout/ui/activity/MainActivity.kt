package com.yan.takeout.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.R
import com.yan.takeout.ui.fragment.HomeFragment
import com.yan.takeout.ui.fragment.MeFragment
import com.yan.takeout.ui.fragment.MoreFragment
import com.yan.takeout.ui.fragment.OrderFragment
import com.yan.takeout.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.dip

class MainActivity : AppCompatActivity() {

    val fragments = listOf(HomeFragment(), OrderFragment(), MeFragment(), MoreFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            val dp50 = dip(50)
            ll_main_activity.setPadding(0, 0 ,0, dp50)
        }
        initBottomBar()
        //默认选中首页
        changeIndex(0)
    }

    private fun initBottomBar() {
        for (i in 0 until ll_bottom_bar.childCount) {
            //拿到子布局，设置点击监听
            ll_bottom_bar.getChildAt(i).setOnClickListener {
                changeIndex(i)
            }
        }
    }

    private fun changeIndex(index: Int) {
        for (i in 0 until ll_bottom_bar.childCount) {
            val child = ll_bottom_bar.getChildAt(i)
            if (i == index) {
                //选中，禁用效果 enable = false
                setEnable(child, false)
            } else {
                //未选中，enable = true
                setEnable(child, true)
            }
        }
        //切换fragment
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragments[index])
                .commit()
    }

    private fun setEnable(child: View, isEnable: Boolean) {
        if (child is ViewGroup) {
            for (i in 0 until child.childCount) {
                child.getChildAt(i).isEnabled = isEnable
            }
        }
    }
}
