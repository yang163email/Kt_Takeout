package com.yan.takeout.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.heima.takeout.utils.PriceFormater
import com.yan.takeout.kt.R
import com.yan.takeout.kt.ui.adapter.BusinessFragmentPagerAdapter
import com.yan.takeout.kt.ui.fragment.CommentsFragment
import com.yan.takeout.kt.ui.fragment.GoodsFragment
import com.yan.takeout.kt.ui.fragment.SellerFragment
import com.yan.takeout.kt.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_business.*
import org.jetbrains.anko.dip

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 16:00
 *  @description : 商品列表界面
 */
class BusinessActivity: AppCompatActivity() {

    val fragments = listOf(GoodsFragment(), SellerFragment(), CommentsFragment())
    val titles = listOf("商品", "商家", "评论")

    var bottomSheetView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)

        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            fl_Container.setPadding(0, 0 ,0, dip(48))
        }
        //ViewPager以及tab设置
        vp.adapter = BusinessFragmentPagerAdapter(fragmentManager, fragments, titles)
        tabs.setupWithViewPager(vp)

        bottom.setOnClickListener { showCart() }
    }

    private fun showCart() {
        if (bottomSheetView == null) {
            bottomSheetView = layoutInflater.inflate(R.layout.cart_list, null)
        }
        //判断BottomSheetLayout内容是否显示
        if (bottomSheetLayout.isSheetShowing) {
            //关闭显示
            bottomSheetLayout.dismissSheet()
        } else {
            //显示里面的内容
            bottomSheetLayout.showWithSheetView(bottomSheetView)
        }
    }

    /**
     * 添加add图片
     */
    fun addImageButton(ib: ImageView, width: Int, height: Int) {
        fl_Container.addView(ib, width, height)
    }

    /**
     * 获取购物车坐标
     */
    fun getCartLocation(): IntArray {
        val srcLocation = IntArray(2)
        imgCart.getLocationInWindow(srcLocation)
        return srcLocation
    }

    /**
     * 更新购物车UI
     */
    fun updateCartUI() {
        var count = 0   //购物车商品数量
        var countPrice = 0f  //购物车商品总价格
        //获取购物车中的商品
        val goodsFragment = fragments[0] as GoodsFragment
        val cartList = goodsFragment.goodsPresenter.getCartList()
        cartList.forEach {
            count += it.count
            countPrice += it.count * it.newPrice.toFloat()
        }
        tvSelectNum.text = count.toString()
        tvCountPrice.text = PriceFormater.format(countPrice)
        if (count > 0) {
            tvSelectNum.visibility = View.VISIBLE
        } else {
            tvSelectNum.visibility = View.GONE
        }
    }
}