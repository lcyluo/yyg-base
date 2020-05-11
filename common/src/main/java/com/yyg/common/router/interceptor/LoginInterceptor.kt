package com.yyg.common.router.interceptor

import android.annotation.SuppressLint
import com.yyg.common.router.InterceptorConfig
import com.yyg.common.router.RouterConfig
import com.yyg.common.router.service.UserService
import com.xiaojinzi.component.anno.InterceptorAnno
import com.xiaojinzi.component.error.ServiceNotFoundException
import com.xiaojinzi.component.impl.RouterInterceptor
import com.xiaojinzi.component.impl.RxRouter
import com.xiaojinzi.component.impl.service.ServiceManager
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
        val userService = ServiceManager.get(UserService::class.java)
        if (context == null || userService == null) {
            chain.callback().onError(ServiceNotFoundException("can't found UserService"))
            return
        }
        if (userService.isLogin()) {
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