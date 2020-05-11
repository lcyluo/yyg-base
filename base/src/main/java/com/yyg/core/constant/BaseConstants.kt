package com.yyg.core.constant

object BaseConstants {

    /** Http请求头 access-token **/
    const val OK_HTTP_HEADER_TOKEN = "accessToken"
    /** Http请求头 客户端类型 **/
    const val OK_HTTP_HEADER_CLIENT = "mobileType"
    /** Http请求头 版本号 **/
    const val OK_HTTP_HEADER_VERSION_NAME = "version"

    /** 登录当前用户token **/
    const val KEY_SP_LOGIN_USER_TOKEN = "sp_login_user_token"
    /** 登录当前用户手机号 **/
    const val KEY_SP_LOGIN_USER_ACCOUNT = "sp_login_user_account"
    /** 储存当前用户密码 **/
    const val KEY_SP_LOGIN_USER_PASSWORD = "sp_login_user_password"
    /** 刷新token地址 **/
    const val APP_API_LOGIN = "api/user/login"
}