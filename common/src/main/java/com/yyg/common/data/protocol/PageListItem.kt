package com.yyg.common.data.protocol

import com.yyg.common.constant.CommonConstants
import com.google.gson.annotations.SerializedName

/**
 * 列表数据集合
 *
 * @author lh
 */
class PageListItem<T>(
    @SerializedName(value = "current")
    val pageIndex: Int = 1,
    @SerializedName(value = "size")
    val pageSize: Int = CommonConstants.PAGE_SIZE,
    val list: MutableList<T>? = null
)