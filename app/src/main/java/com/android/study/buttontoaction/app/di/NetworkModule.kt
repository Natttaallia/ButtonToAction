package com.android.study.buttontoaction.app.di

import com.android.study.buttontoaction.data.api.ActionsApi
import com.android.study.buttontoaction.data.api.ActionsApi.Companion.provideRetrofit
import com.android.study.buttontoaction.data.api.ActionsApi.Companion.provideService
import com.android.study.buttontoaction.data.mappers.ActionsLastUsedMapper
import org.koin.dsl.module

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
val networkModule = module {
    single { provideRetrofit() }
    single { provideService<ActionsApi>(get()) }
    factory { ActionsLastUsedMapper(get()) }
}