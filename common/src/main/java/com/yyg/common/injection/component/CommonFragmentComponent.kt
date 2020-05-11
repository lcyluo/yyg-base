package com.yyg.common.injection.component

import com.lcy.base.core.injection.component.AppComponent
import com.lcy.base.core.injection.module.FragmentModule
import com.lcy.base.core.injection.scope.FragmentScope
import com.yyg.common.injection.module.CommonDiModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FragmentModule::class, CommonDiModule::class]
)
interface CommonFragmentComponent