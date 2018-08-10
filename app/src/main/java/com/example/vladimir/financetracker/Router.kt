package com.example.vladimir.financetracker

import android.content.Intent
import android.support.v4.app.Fragment
import com.example.vladimir.financetracker.view.fragments.*

class Router {
    fun navigate(intent: Intent?): Fragment? {
        return when (intent?.getStringExtra(Routes.DESTINATION_FRAGMENT)) {
            Routes.ADD_TRANSACTION_FRAGMENT -> FragmentAddTransaction()
            Routes.EDIT_TRANSACTION_FRAGMENT -> FragmentEditTransaction()
            Routes.SETTINGS_FRAGMENT -> FragmentSettings()
            Routes.ABOUT_FRAGMENT -> FragmentAbout()
            Routes.WALLETS_FRAGMENT -> FragmentWallets()
            Routes.STATISTICS_FRAGMENT -> FragmentStatistics()
            Routes.MAIN_FRAGMENT -> FragmentMain()
            Routes.ADD_WALLET_FRAGMENT -> FragmentAddWallet()
            else -> {
                null
            }
        }
    }
}