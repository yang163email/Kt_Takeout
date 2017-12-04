package com.yan.takeout.kt.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.AbsListView
import android.widget.ImageView
import com.yan.takeout.kt.R
import com.yan.takeout.kt.di.component.DaggerGoodsFragmentComponent
import com.yan.takeout.kt.di.module.GoodsFragmentModule
import com.yan.takeout.kt.model.beans.GoodsInfo
import com.yan.takeout.kt.model.beans.GoodsTypeInfo
import com.yan.takeout.kt.presenter.GoodsFragmentPresenter
import com.yan.takeout.kt.ui.activity.BusinessActivity
import com.yan.takeout.kt.ui.adapter.GoodsAdapter
import com.yan.takeout.kt.ui.adapter.GoodsTypeRvAdapter
import com.yan.takeout.kt.utils.AnimListenerAdapter
import com.yan.takeout.kt.utils.AnimationUtil
import kotlinx.android.synthetic.main.fragment_goods.*
import javax.inject.Inject

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 16:26
 *  @description : 商品fragment
 */
class GoodsFragment : Fragment() {
    val TAG = javaClass.simpleName

    @Inject
    lateinit var goodsPresenter: GoodsFragmentPresenter
    private lateinit var goodsAdapter: GoodsAdapter
    //左侧所有类型集合
    private lateinit var goodsTypeList: List<GoodsTypeInfo>
    //右侧所有商品集合
    val goodsList = mutableListOf<GoodsInfo>()

    private var animShowing = false

    private lateinit var parentActivity: BusinessActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = activity as BusinessActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    private lateinit var goodsTypeAdapter: GoodsTypeRvAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        DaggerGoodsFragmentComponent.builder()
                .goodsFragmentModule(GoodsFragmentModule(this))
                .build()
                .inject(this)
        rv_goods_type.apply {
            layoutManager = LinearLayoutManager(activity)
            goodsTypeAdapter = GoodsTypeRvAdapter(activity)
            adapter = goodsTypeAdapter
        }
        goodsAdapter = GoodsAdapter(activity)
        slhlv.adapter = goodsAdapter

        initListener()
    }

    private fun initListener() {
        //监听左侧条目点击事件
        goodsTypeAdapter.setTypeClickListener {
            //遍历所有商品，找到position
            val position = goodsPresenter.getGoodsPositionByTypeId(goodsList, it)
            //选中对应右侧position
            slhlv.setSelection(position)
        }

        goodsAdapter.setOnItemClickListener { view, goodsInfo ->
            //需要找到当前点击的typeId
            val typeId = goodsInfo.typeId
            //找到左侧列表的位置,设置数据
            val typePosition = goodsPresenter.getTypePositionByTypeId(goodsTypeList, typeId)
            if (view.id == R.id.ib_add) {
                //红点数++
                goodsTypeList[typePosition].redDotCount++
                showParabolaAnim(view)
            } else {
                goodsTypeList[typePosition].redDotCount--
            }
            goodsTypeAdapter.setData(goodsTypeList)
            //添加或者减少的时候，都需要更新底部购物车显示
            parentActivity.updateCartUI()
        }
    }

    private fun showParabolaAnim(view: View) {
        //动画正在show，不再进行添加显示
        if (animShowing) return
        //复制一份+图片
        val ib = ImageView(activity)
        ib.setBackgroundResource(R.mipmap.button_add)
        //获取原始位置
        val srcLocation = IntArray(2)
        view.getLocationInWindow(srcLocation)
        ib.x = srcLocation[0].toFloat()
        ib.y = srcLocation[1].toFloat()
        //添加一个ImageButton
        parentActivity.addImageButton(ib, view.width, view.height)

        //执行抛物线动画
        val destLocation = parentActivity.getCartLocation()
        val parabolaAnim = AnimationUtil.getParabolaAnimation(srcLocation, destLocation)
        ib.startAnimation(parabolaAnim)
        parabolaAnim.setAnimationListener(object : AnimListenerAdapter() {
            override fun onAnimationStart(animation: Animation?) {
                animShowing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                animShowing = false
                //动画结束，移除+图片
                val viewParent = ib.parent
                viewParent?.let {
                    (it as ViewGroup).removeView(ib)
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        goodsPresenter.getBusinessInfo("1")
    }

    fun onLoadBusinessSuccess(goodsTypeList: List<GoodsTypeInfo>) {
        this.goodsTypeList = goodsTypeList
        goodsTypeAdapter.setData(goodsTypeList)

        goodsTypeList.forEach { outer->
            val list = outer.list
            list.forEach { inner->
                //遍历，对每个商品对应typeId，typeName进行赋值
                inner.typeId = outer.id
                inner.typeName = outer.name
            }
            //添加到所有商品集合中
            goodsList.addAll(list)
        }
        goodsAdapter.setData(goodsList)
        //有数据才进行滚动监听
        handleScrollEvent(goodsTypeList)
    }

    /**
     * 处理右侧商品滚动事件
     */
    private fun handleScrollEvent(goodsTypeList: List<GoodsTypeInfo>) {
        slhlv.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                //首先找到左侧当前选中的position
                val oldPosition = goodsTypeAdapter.selectPosition
                //根据当前显示的第一个可见的position找到对应的typeId
                val newTypeId = goodsList[firstVisibleItem].typeId
                //根据typeid找到对应左侧的position
                val newPosition = goodsPresenter.getTypePositionByTypeId(goodsTypeList, newTypeId)
                if (oldPosition != newPosition) {
                    //如果当前position与新的position不一致，需要更新类别position的位置
                    goodsTypeAdapter.selectPosition = newPosition
                    //左侧RecyclerView滚动到指定位置
                    rv_goods_type.scrollToPosition(newPosition)
                    goodsTypeAdapter.notifyDataSetChanged()
                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
            }
        })
    }
}