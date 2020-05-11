package com.yyg.core.data.protocol

import com.google.gson.annotations.SerializedName

data class UserInfo(
    /** 用户ID **/
    @SerializedName(value = "userId", alternate = ["accountId"])
    val userId: String?,
    /** 用户账号**/
    var account: String?,
    /** 用户密码 **/
    var password: String?,
    /** 用户名 **/
    var username: String?,
    /** 用户姓名 **/
    val realName: String?,
    /** 用户手机 **/
    val mobile: String?,
    /** 用户头像 **/
    @SerializedName(value = "avatar", alternate = ["photo"])
    val avatar: String?,
    /** token信息 **/
    @SerializedName(value = "token", alternate = ["accessToken"])
    val token: String?,
    /** 团队id **/
    val teamId: String? = null,
    /** 团队名称 **/
    val teamName: String? = null
)