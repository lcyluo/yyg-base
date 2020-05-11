package com.yyg.common.event

/**
 * 消息通知码
 *
 * @author lh
 */
object EventCode {
    /** 切换团队 **/
    const val EVENT_SWITCH_TEAM: Long = 0x10001003
    /** 事件刷新 **/
    const val EVENT_REFRESH: Long = 0x10001004
    /** 事件处理成功 **/
    const val EVENT_HANDLE_SUCCESS: Long = 0x10001005
    /** 刷新用户信息 **/
    const val USER_INFO_REFRESH: Long = 0x10001006
    /** 事件数量刷新 **/
    const val EVENT_COUNT_REFRESH: Long = 0x10001007
    /** 通知数量刷新 **/
    const val NOTICE_COUNT_REFRESH: Long = 0x10001008
}
