package com.example.vladimir.financetracker

import com.example.vladimir.financetracker.R.string.value
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

val roubles = "Рубли"
val RATE = 60.0

fun countBalance(wallet: Wallet, transactions: List<Transaction>): Double {
    var balance = wallet.balance
    transactions.forEach {
        balance += it.value
    }
    return balance
}