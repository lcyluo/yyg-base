package com.yyg.common.data.api

import com.yyg.common.constant.CommonConstants

class ApiQuery<out T>(
    pageIndex: Int = 1,
    pageSize: Int = CommonConstants.PAGE_SIZE,
    val query: T? = null
) : ApiPage(pageIndex, pageSize)