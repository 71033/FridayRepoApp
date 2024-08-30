package com.example.fridayrepoapp

import android.app.Application
import com.example.fridayrepoapp.data.AppContainer
import com.example.fridayrepoapp.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }
}
