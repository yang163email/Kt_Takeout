package com.yan.takeout.kt.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.yan.takeout.kt.R
import kotlinx.android.synthetic.main.item_title.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 10:34
 *  @description : 首页头部view
 */
class HomeTitleItemView : RelativeLayout {
    private val urlMaps = hashMapOf<String, String>()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_title, this)
        if (urlMaps.size == 0) {
            urlMaps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
            urlMaps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
            urlMaps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
            urlMaps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

            urlMaps.forEach {
                val textSliderView = TextSliderView(context)
                textSliderView.description(it.key).image(it.value)
                slider.addSlider(textSliderView)
            }
        }
    }

    fun bindView(s: String) {

    }
}