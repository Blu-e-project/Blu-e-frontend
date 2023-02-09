package com.example.blu_e

import android.app.Application

//@HiltAndroidApp
class MainApplication: Application() {
    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}