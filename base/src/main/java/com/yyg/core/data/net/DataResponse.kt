package com.yyg.core.data.net

class DataResponse<out T>(
    status: Boolean,
    code: Int,
    message: String,
    val data: T
) : BaseResponse(status, code, message)