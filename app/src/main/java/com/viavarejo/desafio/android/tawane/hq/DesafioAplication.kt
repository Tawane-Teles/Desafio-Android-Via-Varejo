package com.viavarejo.desafio.android.tawane.hq

import android.app.Application
import android.content.Context
import com.orhanobut.hawk.Hawk
import com.viavarejo.desafio.android.tawane.hq.di.applicationModule
import com.viavarejo.desafio.android.tawane.hq.di.repositoryModule
import com.viavarejo.desafio.android.tawane.hq.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DesafioAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
        setupKoin()
        setupHawk()
    }

    private fun setupHawk() = Hawk.init(this).build()

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@DesafioAplication)
            androidFileProperties()
            modules(
                applicationModule, repositoryModule, viewModelModule
            )
        }
    }


    companion object {
        lateinit var instance: Context
    }
}