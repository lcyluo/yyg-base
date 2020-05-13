package com.yyg.common.data.net

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    val code: Int,
    @SerializedName(value = "message", alternate = ["msg"])
    val message: String?
)