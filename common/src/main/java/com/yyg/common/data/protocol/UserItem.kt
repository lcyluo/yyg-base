package com.yyg.common.data.protocol

/**
 * 用户信息
 *
 * @author lh
 */
data class UserItem(
    val personId: String,
    val realName: String? = null,
    val photo: String? = null,
    val mobile: String? = null
)