package com.example.vladimir.financetracker

import android.content.Intent
import android.support.v4.app.Fragment
import com.example.vladimir.financetracker.view.fragments.*

class Router {
    fun navigate(intent: Intent?): Fragment? {
        return when (intent?.getStringExtra(Routes.DESTINATION_FRAGMENT)) {
            Routes.TRANSACTION_FRAGMENT -> FragmentTransaction()
            Routes.SETTINGS_FRAGMENT -> FragmentSettings()
            Routes.ABOUT_FRAGMENT -> FragmentAbout()
            Routes.WALLET_FRAGMENT -> FragmentWallet()
            Routes.STATISTICS_FRAGMENT -> FragmentStatistics()
            Routes.MAIN_FRAGMENT -> FragmentMain()
            else -> {
                null
            }
        }
    }
}