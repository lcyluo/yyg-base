package com.yyg.common.data.api

import com.yyg.common.constant.CommonConstants

open class ApiPage(
    val pageIndex: Int = 1,
    val pageSize: Int = CommonConstants.PAGE_SIZE
)