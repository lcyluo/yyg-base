package com.yyg.core.app

import com.lcy.base.core.common.CoreApplication
import com.tencent.smtt.sdk.QbSdk

/**
 * Application基类
 *
 * @author lh
 */
abstract class BaseApplication : CoreApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
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