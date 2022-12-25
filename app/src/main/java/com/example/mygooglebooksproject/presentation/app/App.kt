package com.example.mygooglebooksproject.presentation.app

import android.app.Application
import android.content.Context
import com.example.mygooglebooksproject.presentation.di.AppComponent
import com.example.mygooglebooksproject.presentation.di.AppModule
import com.example.mygooglebooksproject.presentation.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(context = this)).build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }