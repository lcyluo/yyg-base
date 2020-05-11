package com.yyg.common.injection.module

import com.yyg.common.data.net.CommonApis
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CommonDiModule {

    @Provides
    fun provideLoginService(retrofit: Retrofit): CommonApis {
        return retrofit.create(CommonApis::class.java)
    }

}