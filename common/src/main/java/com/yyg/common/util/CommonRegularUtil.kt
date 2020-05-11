package com.yyg.common.util

import java.util.regex.Pattern

/**
 * 正则工具类
 *
 * @author lh
 */
object CommonRegularUtil {
    /**
     * 验证url域名
     */
    private const val REGEX_URL = "^(https?)+://[^\\s]*"

    /**
     * 验证密码6-18位包含数字和字母
     */
    private const val REGEX_PASSWORD = "^(?=.*[a-zA-Z])(?=.*\\d)[^\\\\]{6,18}\$"

    /**
     * 判断域名地址
     *
     * @param baseUrl 域名基地址
     */
    fun isApiUrl(baseUrl: String): Boolean {
        val p = Pattern.compile(REGEX_URL)
        val m = p.matcher(baseUrl)
        return m.matches()
    }


    /**
     * 验证密码6-18位包含数字和字母
     */
    fun isPassword(pwd: String): Boolean {
        val pattern = Pattern.compile(REGEX_PASSWORD)
        val matcher = pattern.matcher(pwd)
        return matcher.matches()
    }

}