package com.example.vladimir.financetracker

import android.content.Intent
import android.support.v4.app.Fragment
import com.example.vladimir.financetracker.fragments.FragmentAbout
import com.example.vladimir.financetracker.fragments.FragmentExpenditure
import com.example.vladimir.financetracker.fragments.FragmentMain
import com.example.vladimir.financetracker.fragments.FragmentSettings

class Navigator {
    fun navigate(intent: Intent?): Fragment? {
        return when (intent?.getStringExtra(NavigationConstants.DESTINATION_FRAGMENT)) {
            NavigationConstants.EXPENDITURE_FRAGMENT -> FragmentExpenditure()
            NavigationConstants.SETTINGS_FRAGMENT -> FragmentSettings()
            NavigationConstants.ABOUT_FRAGMENT -> FragmentAbout()
            else -> {
                null
            }
        }
    }
}