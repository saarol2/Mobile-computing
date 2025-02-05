package com.example.composetutorial

import android.app.Application

class MobileApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}