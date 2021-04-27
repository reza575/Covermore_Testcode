package com.moeiny.reza.covermore_testcode

import android.app.Application
import com.moeiny.reza.covermore_testcode.core.di.ApplicationComponent
import com.moeiny.reza.covermore_testcode.core.di.ApplicationModule
import com.moeiny.reza.covermore_testcode.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {

    val component : ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}