package com.yyg.common.data.net

import com.yyg.core.constant.BaseConstants
import com.yyg.core.data.net.DataResponse
import com.yyg.core.data.protocol.UserInfo
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface CommonApis {

    /**
     * 用户登录
     */
    @Headers(
        "loginChannel: APP",
        "encryption: true"
    )
    @POST(BaseConstants.APP_API_LOGIN)
    @FormUrlEncoded
    fun accountLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<DataResponse<UserInfo>>

}