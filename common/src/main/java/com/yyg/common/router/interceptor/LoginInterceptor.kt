package com.yyg.common.router.interceptor

import android.annotation.SuppressLint
import com.lcy.base.core.utils.SpUtil
import com.xiaojinzi.component.anno.InterceptorAnno
import com.xiaojinzi.component.error.ServiceNotFoundException
import com.xiaojinzi.component.impl.RouterInterceptor
import com.xiaojinzi.component.impl.RxRouter
import com.yyg.common.constant.CommonConstants
import com.yyg.common.router.InterceptorConfig
import com.yyg.common.router.RouterConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 登录拦截器
 *
 * @author lh
 */
@InterceptorAnno(InterceptorConfig.USER_LOGIN)
class LoginInterceptor : RouterInterceptor {

    /**
     * 拦截器的拦截方法, 一定是在主线程中被执行的, 您不必担心此方法的线程安全问题
     *
     * @param chain 拦截器执行连接器
     */
    @SuppressLint("CheckResult")
    override fun intercept(chain: RouterInterceptor.Chain) {
        //  登录界面不拦截
        val loginHostAndPath = "${RouterConfig.App.HOST_NAME}/${RouterConfig.App.PATH_LOGIN}"
        if (chain.request().uri.toString().contains(loginHostAndPath)) {
            chain.proceed(chain.request())
            return
        }
        val context = chain.request().rawContext
        if (context == null) {
            chain.callback().onError(ServiceNotFoundException("context is null"))
            return
        }
        val isLogin = SpUtil.getInstance().getBoolean(CommonConstants.KEY_SP_LOGIN_STATE, false)
        if (isLogin) {
            chain.proceed(chain.request())
            return
        }
        RxRouter.with(context)
            .host(RouterConfig.App.HOST_NAME)
            .path(RouterConfig.App.PATH_LOGIN)
            .requestCodeRandom()
            .intentCall()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { chain.proceed(chain.request()) },
                { chain.callback().onError(Exception("login fail")) })
    }
}