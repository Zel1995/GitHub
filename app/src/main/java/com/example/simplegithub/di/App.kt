package com.example.simplegithub.di

import android.app.Application
import com.example.simplegithub.di.modules.ApplicationModule

class App : Application() {
    val appComponent = DaggerAppComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()

}