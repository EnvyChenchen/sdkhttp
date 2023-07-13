package com.ifun.ifunokhttp

import android.app.Application

/**
 * @Auther:
 * @desc:
 */
class MyApp :Application() {
    companion object{
        var app:Application? = null
    }
    override fun onCreate() {
        super.onCreate()
        app = this
    }
}