package com.kkk.kopilot

import android.app.Application
import org.koin.core.KoinApplication

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KoinApplication.start()
    }
}