package com.example.myapplication

import android.app.Application
import lib.common.CAPP

class APP:Application(){
    override fun onCreate() {
        super.onCreate()
        CAPP.getInstance().init(this)
    }
}