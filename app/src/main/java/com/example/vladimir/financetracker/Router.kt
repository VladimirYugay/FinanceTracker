package com.example.vladimir.financetracker

import android.content.Intent
import android.support.v4.app.Fragment
import com.example.vladimir.financetracker.activities.FragmentWallet
import com.example.vladimir.financetracker.fragments.FragmentAbout
import com.example.vladimir.financetracker.fragments.FragmentExpenditure
import com.example.vladimir.financetracker.fragments.FragmentSettings

class Router {
    fun navigate(intent: Intent?): Fragment? {
        return when (intent?.getStringExtra(Routes.DESTINATION_FRAGMENT)) {
            Routes.EXPENDITURE_FRAGMENT -> FragmentExpenditure()
            Routes.SETTINGS_FRAGMENT -> FragmentSettings()
            Routes.ABOUT_FRAGMENT -> FragmentAbout()
            Routes.WALLET_FRAGMENT -> FragmentWallet()
            else -> {
                null
            }
        }
    }
}