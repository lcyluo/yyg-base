package com.yyg.common.data.net

class DataResponse<out T>(
    code: Int,
    message: String,
    val data: T
) : BaseResponse(code, message)