package com.yan.takeout.kt.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.heima.takeout.utils.PriceFormater
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.beans.Seller
import com.yan.takeout.kt.ui.adapter.BusinessFragmentPagerAdapter
import com.yan.takeout.kt.ui.adapter.CartRvAdapter
import com.yan.takeout.kt.ui.fragment.CommentsFragment
import com.yan.takeout.kt.ui.fragment.GoodsFragment
import com.yan.takeout.kt.ui.fragment.SellerFragment
import com.yan.takeout.kt.utils.DeviceUtil
import com.yan.takeout.kt.utils.EventBusTag
import kotlinx.android.synthetic.main.activity_business.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.dip
import org.jetbrains.anko.find
import org.simple.eventbus.EventBus

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 16:00
 *  @description : 商品列表界面
 */
class BusinessActivity: AppCompatActivity() {

    val fragments = listOf(GoodsFragment(), SellerFragment(), CommentsFragment())
    val titles = listOf("商品", "商家", "评论")

    var bottomSheetView: View? = null
    lateinit var rvCart: RecyclerView
    lateinit var tvClear: TextView

    lateinit var cartRvAdapter: CartRvAdapter
    var clearCartDialog: AlertDialog? = null

    var hasSelectInfo: Boolean = false
    lateinit var seller: Seller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)

        handleIntent()
        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            fl_Container.setPadding(0, 0 ,0, dip(48))
        }
        //ViewPager以及tab设置
        vp.adapter = BusinessFragmentPagerAdapter(fragmentManager, fragments, titles)
        tabs.setupWithViewPager(vp)

        cartRvAdapter = CartRvAdapter(this)
        initListener()
    }

    private fun handleIntent() {
        intent?.let {
            hasSelectInfo = it.getBooleanExtra("hasSelectInfo", false)
            seller = it.getSerializableExtra("seller") as Seller
            tvDeliveryFee.text = "另需配送费" + PriceFormater.format(seller.deliveryFee.toFloat())
            tvSendPrice.text = PriceFormater.format(seller.sendPrice.toFloat()) + "起送"
        }
    }

    private fun initListener() {
        bottom.setOnClickListener { showOrHideCart() }
        cartRvAdapter.setOnCloseBottomListener { showOrHideCart() }
    }

    private fun showOrHideCart() {
        if (bottomSheetView == null) {
            bottomSheetView = layoutInflater.inflate(R.layout.cart_list, null)
            rvCart = bottomSheetView!!.find(R.id.rvCart)
            tvClear = bottomSheetView!!.find(R.id.tvClear)
            rvCart.apply {
                layoutManager = LinearLayoutManager(this@BusinessActivity)
                adapter = cartRvAdapter
            }
            tvClear.setOnClickListener { showClearCartDialog() }
        }
        //判断BottomSheetLayout内容是否显示
        if (bottomSheetLayout.isSheetShowing) {
            //关闭显示
            bottomSheetLayout.dismissSheet()
        } else {
            //显示里面的内容
            val goodsFragment = fragments[0] as GoodsFragment
            val cartList = goodsFragment.goodsPresenter.getCartList()
            cartRvAdapter.setCartList(cartList)
            if (!cartList.isEmpty()) {
                bottomSheetLayout.showWithSheetView(bottomSheetView)
            }
        }
    }

    private fun showClearCartDialog() {
        if (clearCartDialog == null) {
            clearCartDialog = alert("确定不吃了吗？", null) {
                positiveButton("是，不吃了") {
                    //清空购物车
                    //数据层
                    val goodsFragment = fragments[0] as GoodsFragment
                    goodsFragment.goodsPresenter.clearCart()
                    //ui刷新
                    cartRvAdapter.notifyDataSetChanged()
                    //关闭购物车
                    showOrHideCart()
                    EventBus.getDefault().post(1, EventBusTag.TAG_CLEAR_CART)
                }
                negativeButton("不，我还要吃") {}
            }.build()
        }
        clearCartDialog!!.show()
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