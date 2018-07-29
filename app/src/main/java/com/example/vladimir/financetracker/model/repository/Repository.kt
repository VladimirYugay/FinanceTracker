package com.example.vladimir.financetracker.model.repository

import android.arch.lifecycle.MutableLiveData
import com.example.vladimir.financetracker.createId
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

class Repository {

    val wallets: MutableList<Wallet> = mutableListOf()

    private object Holder {
        val INSTANCE = Repository()
    }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }

    fun initData() {
        //SIMULATE DATA FROM DB
        val transaction = Transaction(createId(), "Burger", "BUY", "RUB", "FOOD", 100.0, "1984")
        val transaction2 = Transaction(createId(), "Sandwich", "BUY", "USD", "FOOD", 80.0, "1984")
        val transaction3 = Transaction(createId(), "Latte", "BUY", "RUB", "FOOD", 150.0, "1984")
        val transaction4 = Transaction(createId(), "Espresso", "BUY", "RUB", "FOOD", 150.0, "1984")
        val transaction5 = Transaction(createId(), "Tea", "BUY", "RUB", "FOOD", 150.0, "1984")
        val wallet1 = Wallet("WALLET", 250.0, mutableListOf(transaction, transaction2, transaction3, transaction4, transaction5), false)

        val transaction6 = Transaction(createId(), "Naruto", "BUY", "RUB", "FOOD", 100.0, "1984")
        val transaction7 = Transaction(createId(), "Sandwich", "BUY", "USD", "FOOD", 80.0, "1984")
        val transaction8 = Transaction(createId(), "Latte", "BUY", "RUB", "FOOD", 150.0, "1984")
        val transaction9 = Transaction(createId(), "Karamba", "BUY", "RUB", "FOOD", 150.0, "1984")
        val transaction10 = Transaction(createId(), "Trara", "BUY", "RUB", "FOOD", 150.0, "1984")
        val wallet2 = Wallet("WALLET2", 550.0, mutableListOf(transaction6, transaction7, transaction8, transaction9, transaction10), true)

        wallets.add(wallet1)
        wallets.add(wallet2)
    }

    fun getSelectedWallet(): Wallet? {
        wallets.forEach {
            if (it.selected) return it
        }
        return null
    }


    fun addWallet(wallet: Wallet) {
        wallets.add(wallet)
    }


    fun getSelectedTransactions(): MutableList<Transaction>? {
        wallets.forEach {
            if (it.selected) return it.transactions
        }
        return null
    }


    fun addTransaction(transaction: Transaction){
        wallets.forEach {
            if(it.selected) it.transactions.add(transaction )
        }
    }
}