package com.yyg.common.constant

import com.yyg.core.app.BaseApplication

object CommonConstants {

    /** 多次触发点击事件 **/
    const val WINDOW_DURATION_SHORT = 500L

    /** file provider **/
    var APP_FILE_PROVIDER = "${BaseApplication.instance().packageName}.fileprovider"

    /** 图片选择最大图片数量 **/
    const val MAX_PICKER_PHOTO_COUNT = 9

    /** 分页页码数 **/
    const val PAGE_SIZE = 20

    /** 存储登录状态 **/
    const val KEY_SP_LOGIN_STATE = "sp_login_state"

    /** 登录当前用户ID **/
    const val KEY_SP_LOGIN_USER_ID = "sp_login_user_id"

    /** 登录当前用户团队ID **/
    const val KEY_SP_LOGIN_TEAM_ID = "sp_login_team_id"

    /** 登录当前用户团队名称 **/
    const val KEY_SP_LOGIN_TEAM_NAME = "sp_login_team_name"

    /** 验证码发送时间 **/
    const val KEY_SP_CODE_TIME = "sp_code_time"

    /** 我的已办 **/
    const val MY_DONE = 1000

    /** 我的上报 **/
    const val MY_REPORT = 1001

    /** 验证码发送间隔 **/
    const val CODE_INTERVAL = 60 * 1000

}