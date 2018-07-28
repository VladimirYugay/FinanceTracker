package com.example.vladimir.financetracker.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.vladimir.financetracker.createId
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

class Repository {

    val wallets: MutableLiveData<MutableList<Wallet>> = MutableLiveData()
    val transactions: MutableLiveData<MutableList<Transaction>> = MutableLiveData()

    private object Holder {
        val INSTANCE = Repository()
    }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }


    fun getLiveTransactions(): MutableLiveData<MutableList<Transaction>>{
        val transaction = Transaction(createId(), "Burger", "BUY", "RUB", "FOOD", 100.0, "1984")
        val transaction2 = Transaction(createId(), "Sandwich", "BUY", "USD", "FOOD", 80.0, "1984")
        val transaction3 = Transaction(createId(), "Latte", "BUY", "RUB", "FOOD", 150.0, "1984")
        val transaction4 = Transaction(createId(), "Latte", "BUY", "RUB", "FOOD", 150.0, "1984")
        val transaction5 = Transaction(createId(), "Latte", "BUY", "RUB", "FOOD", 150.0, "1984")
        transactions.value = mutableListOf(transaction, transaction2, transaction3, transaction4, transaction5)
        return transactions
    }


}