package com.example.vladimir.financetracker

import android.app.Application

class FinanceTrackerApplication : Application() {

    companion object {
        @JvmStatic lateinit var INSTANCE : FinanceTrackerApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}