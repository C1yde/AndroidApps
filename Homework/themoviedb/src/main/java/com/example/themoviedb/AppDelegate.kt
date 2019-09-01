package com.example.themoviedb

import android.app.Application

import com.facebook.stetho.Stetho

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}