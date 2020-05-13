package com.yyg.common.app

import android.app.Application
import android.content.Context
import com.yyg.common.data.db.LibraryModule
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IComponentApplication
import com.yyg.common.data.db.ext.RealmConfigStore
import com.yyg.common.util.LogUtil
import io.realm.Realm
import io.realm.RealmConfiguration

@ModuleAppAnno
class ComponentCommonApplication : IComponentApplication {

    /**
     * 模块被加载的时候回被调用
     *
     * @param app
     */
    override fun onCreate(app: Application) {
        LogUtil.d("Common 模块初始化!")
        initRealm(app)
    }

    private fun initRealm(context: Context) {

        Realm.init(context)

        val libraryConfig = RealmConfiguration.Builder()
            .name(DB_NAME)
            .schemaVersion(DB_VERSION)
            .deleteRealmIfMigrationNeeded()
            .build()
        RealmConfigStore.initModule(LibraryModule::class.java, libraryConfig)
    }

    /**
     * 模块被卸载的时候会被调用,该模块被卸载,对应的路由也会被卸载,也就是表现为跳转不过去了
     */
    override fun onDestroy() {

    }

    companion object {
        /**
         * 数据库名称
         */
        private const val DB_NAME = "qtouch.common.realm"

        /**
         * 数据库版本
         */
        private const val DB_VERSION = 1L
    }

}