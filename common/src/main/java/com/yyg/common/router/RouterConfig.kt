package com.yyg.common.router

object RouterConfig {

    /** 登录完成后是否直接跳转到首页 **/
    const val DIRECT_TO_HOME = "DIRECT_TO_HOME"

    /** app模块 **/
    object App {
        /** host **/
        const val HOST_NAME = "app"

        /** path **/
        const val PATH_LOGIN = "login"
        const val PATH_MAIN = "main"
    }

    /** user模块 **/
    object User {
        /** host **/
        const val HOST_NAME = "user"

        /** path **/
        const val PATH_INDEX = "user/index"
    }

    /** message模块 **/
    object Order {
        /** host **/
        const val HOST_NAME = "order"

        /** path **/
        const val PATH_INDEX = "order/index"
        const val PATH_TYPE = "order/type"
    }
}