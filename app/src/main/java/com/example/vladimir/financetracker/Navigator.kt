package com.example.vladimir.financetracker

import android.content.Intent
import android.support.v4.app.Fragment
import com.example.vladimir.financetracker.fragments.FragmentExpenditure
import com.example.vladimir.financetracker.fragments.FragmentMain

class Navigator {
    public fun navigate(intent: Intent?): Fragment {
        when (intent?.getStringExtra(NavigationConstants.DESTINATION_FRAGMENT)) {
            NavigationConstants.EXPENDITURE_FRAGMENT -> return FragmentExpenditure()
            else -> {
                return FragmentMain()
            }
        }
    }
}