package com.example.fmmusic

import android.app.Application
import com.example.fmmusic.di.AppComponent
import com.example.fmmusic.di.AppModule
import com.example.fmmusic.di.DaggerAppComponent

class App : Application()  {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}