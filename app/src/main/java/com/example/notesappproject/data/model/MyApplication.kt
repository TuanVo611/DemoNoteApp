package com.example.notesappproject.data.model

import android.annotation.SuppressLint
import android.app.Application

class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var INSTANCE: MyApplication

        fun getInstance(): MyApplication {
            return INSTANCE
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}