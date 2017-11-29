package com.yan.takeout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomBar()
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
    }

    private fun setEnable(child: View, isEnable: Boolean) {
        if (child is ViewGroup) {
            for (i in 0 until child.childCount) {
                child.getChildAt(i).isEnabled = isEnable
            }
        }
    }
}
