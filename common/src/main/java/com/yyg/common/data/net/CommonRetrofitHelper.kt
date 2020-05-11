package com.yyg.common.data.net

import com.yyg.common.data.api.ApiLogin
import com.yyg.core.data.net.DataResponse
import com.yyg.core.data.protocol.UserInfo
import io.reactivex.Flowable
import javax.inject.Inject

class CommonRetrofitHelper @Inject constructor(private val mApiService: CommonApis) {

    fun accountLogin(apiLogin: ApiLogin): Flowable<DataResponse<UserInfo>> {
        return mApiService.accountLogin(apiLogin.userName, apiLogin.password)
    }

}