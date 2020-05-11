package com.yyg.core.event

/**
 * 通知
 *
 * @author lh
 */
data class BaseEvent<T>(
    var code: Long = 0,
    var value: T
)
