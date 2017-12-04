package com.yan.takeout.kt.model.dao

import com.yan.takeout.kt.model.beans.CacheSelectedInfo
import com.yan.takeout.kt.utils.Constants
import java.util.concurrent.CopyOnWriteArrayList

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/4 16:11
 *  @description : 选择商品缓存dao层
 */
object CacheSelectInfoDao {
    var infos = CopyOnWriteArrayList<CacheSelectedInfo>()

    fun queryCacheSelectedInfoByGoodsId(goodsId: Int): Int {
        var count = 0
        infos.forEach {
            val (_,_,goodsId2,count2) = it
            if (goodsId == goodsId2) {
                count = count2
                return@forEach
            }
        }
        return count
    }

    fun queryCacheSelectedInfoByTypeId(typeId: Int): Int {
        var count = 0
        infos.forEach {
            val (_, typeId1, _, count1) = it
            if (typeId1 == typeId) {
                count += count1
            }
        }
        return count
    }

    fun queryCacheSelectedInfoBySellerId(sellerId: Int): Int {
        var count = 0
        infos.forEach {
            val (sellerId1, _, _, count1) = it
            if (sellerId1 == sellerId) {
                count += count1
            }
        }
        return count
    }

    fun addCacheSelectedInfo(info: CacheSelectedInfo) {
        infos.add(info)
    }

    fun clearCacheSelectedInfo(sellerId: Int) {
        val temp = ArrayList<CacheSelectedInfo>()
        infos.forEach {
            if (it.sellerId == sellerId) {
//                infos.remove(info)
                temp.add(it)
            }
        }
        infos.removeAll(temp)
    }

    fun deleteCacheSelectedInfo(goodsId: Int) {
        infos.forEach {
            if (it.goodsId == goodsId) {
                infos.remove(it)
                return
            }
        }
    }

    fun updateCacheSelectedInfo(goodsId: Int, operation: Int) {
        infos.forEach {
            if (it.goodsId == goodsId) {
                when (operation) {
                    Constants.ADD -> it.count ++
                    Constants.MINUS -> it.count --
                }
            }
        }
    }
}