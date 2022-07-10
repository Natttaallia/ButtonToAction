package com.android.study.buttontoaction.app.di

import android.app.Application
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
val dataModule = module {
    single {
        getSharedPrefs(androidApplication())
    }
    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences(
        "ButtonToActionApp",
        android.content.Context.MODE_PRIVATE
    )
}