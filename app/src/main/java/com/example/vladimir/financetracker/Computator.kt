package com.example.vladimir.financetracker

import com.example.vladimir.financetracker.R.string.value
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

class Computator {

    val roubles = "Рубли"

    fun countBalance(wallet: Wallet, transactions: List<Transaction>): Double {
        var balance = wallet.balance
        transactions.map {
            if(it.currency == roubles) it.copy(value = it.value / 60.0)
            else it
        }.forEach {
            if(it.expenditure) balance -= it.value
            else balance += value
        }
        return balance
    }

}