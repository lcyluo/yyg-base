package com.yyg.common.injection.component

import com.yyg.common.injection.module.CommonDiModule
import com.lcy.base.core.injection.component.AppComponent
import com.lcy.base.core.injection.module.ActivityModule
import com.lcy.base.core.injection.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, CommonDiModule::class]
)
interface CommonActivityComponent {

}