package com.yyg.common.data.db.table

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * 域名信息
 *
 * @author lh
 */
open class Domain() : RealmObject() {
    /** 域名名称 **/
    @PrimaryKey
    var domainName: String? = null

    /** 更新时间 **/
    var updateTimeMillis: Long = 0

    /** 是否是默认地址 **/
    var defaultDomain: Boolean = false

    /** 是否是当前连接地址 **/
    var connected: Boolean = false

    constructor(
        domainName: String,
        default: Boolean = false,
        connected: Boolean = false,
        updateTimeMillis: Long = System.currentTimeMillis()
    ) : this() {
        this.defaultDomain = default
        this.connected = connected
        this.domainName = domainName
        this.updateTimeMillis = updateTimeMillis
    }

}