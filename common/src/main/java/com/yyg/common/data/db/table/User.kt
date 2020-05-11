package com.yyg.common.data.db.table

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * 用户信息
 *
 * @author lh
 */
open class User() : RealmObject() {
    /** 用户ID **/
    @PrimaryKey
    var userId: String? = null

    /** 账号 **/
    var account: String? = null

    /** 密码 **/
    var password: String? = null

    /** 用户名 **/
    var username: String? = null

    /** 姓名 **/
    var realName: String? = null

    /** 手机号 **/
    var mobile: String? = null

    /** 头像 **/
    var avatar: String? = null

    /** token **/
    var token: String? = null

    /** 团队id **/
    var teamId: String? = null

    /** 团队名称 **/
    var teamName: String? = null

    /** 更新时间 **/
    var updateTimeMillis: Long = 0

    constructor(
        userId: String?,
        account: String?,
        password: String?,
        username: String?,
        realName: String? = null,
        mobile: String? = null,
        avatar: String? = null,
        token: String? = null,
        teamId: String? = null,
        teamName: String? = null,
        updateTimeMillis: Long = System.currentTimeMillis()
    ) : this() {
        this.userId = userId
        this.account = account
        this.password = password
        this.username = username
        this.realName = realName
        this.mobile = mobile
        this.avatar = avatar
        this.token = token
        this.teamId = teamId
        this.teamName = teamName
        this.updateTimeMillis = updateTimeMillis
    }
}