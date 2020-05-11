package com.yyg.common.injection.helper

import com.lcy.base.core.injection.component.AppComponent
import com.lcy.base.core.injection.module.ActivityModule
import com.lcy.base.core.injection.module.FragmentModule

import com.yyg.common.injection.component.CommonActivityComponent
import com.yyg.common.injection.component.CommonFragmentComponent
import com.yyg.common.injection.component.DaggerCommonActivityComponent
import com.yyg.common.injection.component.DaggerCommonFragmentComponent

/**
 * 依赖注入
 *
 * @author lh
 */
object CommonDiHelper {

    fun getActivityComponent(appComponent: AppComponent, activityModule: ActivityModule)
            : CommonActivityComponent {
        return DaggerCommonActivityComponent.builder()
            .appComponent(appComponent)
            .activityModule(activityModule)
            .build()
    }

    fun getFragmentComponent(appComponent: AppComponent, fragmentModule: FragmentModule)
            : CommonFragmentComponent {
        return DaggerCommonFragmentComponent.builder()
            .appComponent(appComponent)
            .fragmentModule(fragmentModule)
            .build()
    }

}