package com.yyg.core.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * 自定义日志
 *
 * @author lh
 */
object LogUtil {

    fun init(isLoggable: Boolean = true, logTag: String = "LCY") {
        val formatStrategy: PrettyFormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodOffset(2)
            .tag(logTag)
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return isLoggable
            }
        })
    }

    fun v(log: String = "", vararg args: Any) {
        Logger.v(log, args)
    }

    fun d(log: String = "") {
        Logger.d(log)
    }

    fun i(log: String = "") {
        Logger.i(log)
    }

    fun w(log: String = "") {
        Logger.w(log)
    }

    fun e(log: String = "") {
        Logger.e(log)
    }

    fun wtf(log: String = "") {
        Logger.wtf(log)
    }

    fun json(json: String = "") {
        Logger.json(json)
    }

    fun xml(xml: String = "") {
        Logger.xml(xml)
    }

}
