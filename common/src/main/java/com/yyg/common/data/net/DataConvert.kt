package com.yyg.common.data.net

import com.lcy.base.core.data.net.ApiException
import com.yyg.common.constant.ApiConstants
import io.reactivex.Flowable
import io.reactivex.functions.Function

/**
 * 通用数据类型转换封装
 */
class DataConvert<T> : Function<DataResponse<T>, Flowable<T>> {
    override fun apply(t: DataResponse<T>): Flowable<T> {
        if (t.code != ApiConstants.SUCCESS || t.data == null) {
            return Flowable.error(ApiException(t.code, t.message))
        }
        return Flowable.just(t.data)
    }
}

class SuccessConvert : Function<BaseResponse, Flowable<Boolean>> {
    override fun apply(t: BaseResponse): Flowable<Boolean> {
        if (t.code != 0) {
            return Flowable.error(ApiException(t.code, t.message))
        }
        return Flowable.just(true)
    }
}

fun <T> Flowable<DataResponse<T>>.dataConvert(): Flowable<T> {
    return this.flatMap(DataConvert())
}

fun Flowable<BaseResponse>.successConvert(): Flowable<Boolean> {
    return this.flatMap(SuccessConvert())
}

