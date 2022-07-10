package com.android.study.buttontoaction.app

import android.app.Application
import com.android.study.buttontoaction.app.di.dataModule
import com.android.study.buttontoaction.app.di.mainModule
import com.android.study.buttontoaction.app.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author Kulbaka Nataly
 * @date 10.07.2022
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(dataModule, networkModule, mainModule))
        }
    }
}