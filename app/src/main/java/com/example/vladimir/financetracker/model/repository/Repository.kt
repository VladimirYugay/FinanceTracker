package com.example.vladimir.financetracker.model.repository

import android.arch.lifecycle.MutableLiveData
import com.example.vladimir.financetracker.createId
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

class Repository {

    val wallets: MutableList<Wallet> = mutableListOf()
    val transactions: MutableList<Transaction> = mutableListOf()

    private object Holder {
        val INSTANCE = Repository()
    }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }

    fun initData() {
        //SIMULATE DATA FROM DB
        val wallet1 = Wallet(createId(), "Кошелек", 250.0)
//        val wallet2 = Wallet(createId(), "WALLET2", 550.0)
//        val wallet3 = Wallet(createId(), "WALLET3", 750.0)
//        val wallet4 = Wallet(createId(), "WALLET4", 150.0)
        wallets.addAll(mutableListOf(wallet1))

//        val transaction = Transaction(createId(), "Burger", true, "RUB", "FOOD", 100.0, "1984", wallet1.id)
//        val transaction2 = Transaction(createId(), "Sandwich", true, "USD", "FOOD", 80.0, "1984", wallet1.id)
//        val transaction3 = Transaction(createId(), "Latte", true, "RUB", "FOOD", 150.0, "1984", wallet2.id)
//        val transaction4 = Transaction(createId(), "Espresso", true, "RUB", "FOOD", 150.0, "1984", wallet3.id)
//        val transaction5 = Transaction(createId(), "Tea", true, "RUB", "FOOD", 150.0, "1984", wallet4.id)
//        val transaction6 = Transaction(createId(), "Naruto", true, "RUB", "FOOD", 100.0, "1984", wallet4.id)
//        transactions.addAll(mutableListOf(transaction, transaction2, transaction3, transaction4, transaction5, transaction6))
    }

    fun addWallet(wallet: Wallet) {
        wallets.add(wallet)
    }

    fun getWallet(name: String) : Wallet?{
        wallets.forEach {
            if(it.equals(name)) return it
        }
        return null
    }

    fun getAllWallets(): MutableList<Wallet>{
        return wallets
    }

    fun getTransactions(walletName: String): MutableList<Transaction>{
        return transactions
    }

    fun addTransaction(transaction: Transaction){
        transactions.add(transaction)
    }
}