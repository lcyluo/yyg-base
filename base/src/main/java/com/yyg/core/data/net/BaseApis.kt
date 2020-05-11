package com.yyg.core.data.net

import com.yyg.core.constant.BaseConstants
import com.yyg.core.data.protocol.UserInfo
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface BaseApis {
    /**
     * 刷新用户token
     */
    @Headers(
        "loginChannel: APP",
        "encryption: true"
    )
    @POST(BaseConstants.APP_API_LOGIN)
    @FormUrlEncoded
    fun refreshAccessToken(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<DataResponse<UserInfo>>

}