package com.example.vladimir.financetracker.model.repository

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
        val wallet1 = Wallet(createId(), "WALLET", 250.0,  true)
        val wallet2 = Wallet(createId(), "WALLET2", 550.0, false)
        val wallet3 = Wallet(createId(), "WALLET3", 750.0, false)
        val wallet4 = Wallet(createId(), "WALLET4", 150.0, false)
        wallets.addAll(mutableListOf(wallet1, wallet2, wallet3, wallet4))

        val transaction = Transaction(createId(), "Burger", "BUY", "RUB", "FOOD", 100.0, "1984", wallet1.id)
        val transaction2 = Transaction(createId(), "Sandwich", "BUY", "USD", "FOOD", 80.0, "1984", wallet1.id)
        val transaction3 = Transaction(createId(), "Latte", "BUY", "RUB", "FOOD", 150.0, "1984", wallet2.id)
        val transaction4 = Transaction(createId(), "Espresso", "BUY", "RUB", "FOOD", 150.0, "1984", wallet3.id)
        val transaction5 = Transaction(createId(), "Tea", "BUY", "RUB", "FOOD", 150.0, "1984", wallet4.id)
        val transaction6 = Transaction(createId(), "Naruto", "BUY", "RUB", "FOOD", 100.0, "1984", wallet4.id)
        transactions.addAll(mutableListOf(transaction, transaction2, transaction3, transaction4, transaction5, transaction6))
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


    fun getSelectedTransactions(): MutableList<Transaction> {
        val data: MutableList<Transaction> = mutableListOf()
        wallets.forEach {
            if (it.selected){
                val wallet = it
                transactions.forEach {
                    if(it.wallet == wallet.id){
                        data.add(it)
                    }
                }
            }
        }
        return data
    }


    fun addTransaction(transaction: Transaction){
        wallets.forEach {
            if(it.selected) transactions.add(transaction.copy(wallet = it.id))
        }
    }
}