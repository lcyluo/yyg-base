package com.yyg.common.util

/**
 * 公用的转换类
 *
 * @author lh
 */
object ConvertUtil {

    /**
     * 将MutableList转换为ArrayList
     */
    fun <T> mutableListToArrayList(list: MutableList<T>): ArrayList<T> {
        val result = arrayListOf<T>()
        if (list.isNullOrEmpty()) return result
        list.forEach {
            result.add(it)
        }
        return result
    }
}