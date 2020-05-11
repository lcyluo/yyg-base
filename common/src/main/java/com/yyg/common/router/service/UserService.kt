package com.yyg.common.router.service

import com.yyg.core.data.protocol.UserInfo

/**
 * 用户是否已登录
 *
 * @author lh
 */
interface UserService {

    /**
     * 是否登录
     *
     * @return
     */
    fun isLogin(): Boolean

    /**
     * 获取登录的用户信息
     */
    fun getLoginUser(): UserInfo?

}