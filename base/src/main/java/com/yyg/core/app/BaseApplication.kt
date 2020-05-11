package com.yyg.core.app

import com.lcy.base.core.common.CoreApplication
import com.lcy.base.core.common.HttpConfig
import com.lcy.base.core.injection.component.DaggerAppComponent
import com.lcy.base.core.injection.module.AppModule
import com.lcy.base.core.injection.module.HttpModule
import com.tencent.smtt.sdk.QbSdk
import com.yyg.core.BuildConfig
import com.yyg.core.data.net.interceptor.TimeoutInterceptor
import com.yyg.core.data.net.interceptor.TokenInterceptor
import com.yyg.core.data.net.interceptor.UserAgentInterceptor
import com.yyg.core.utils.LogUtil

/**
 * Application基类
 *
 * @author lh
 */
open class BaseApplication : CoreApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        LogUtil.init(BuildConfig.DEBUG)
        // 初始化App接口注入
        initAppInjection()
        // 初始化腾讯X5
        initQbSdk()
    }

    /**
     * 全局伴生对象
     */
    companion object {

        private lateinit var instance: CoreApplication

        fun instance() = instance
    }


    /**
     * 自定义模块信息
     */
    override fun initAppInjection() {
        val httpConfig = HttpConfig.Builder()
            .baseUrl(BuildConfig.API_HOST)
            .showLog(BuildConfig.LOG_ENABLE)
            .addInterceptors(
                listOf(
                    TimeoutInterceptor(),
                    UserAgentInterceptor(),
                    TokenInterceptor()
                )
            )
            .connectTimeout(5L)
            .canProxy(false)
            .build()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .httpModule(HttpModule(httpConfig))
            .build()
    }

    private fun initQbSdk() {
        val qb: QbSdk.PreInitCallback =
            object : QbSdk.PreInitCallback {
                override fun onViewInitFinished(success: Boolean) {}
                override fun onCoreInitFinished() {}
            }
        // x5内核初始化接口
        QbSdk.initX5Environment(applicationContext, qb)
    }
}