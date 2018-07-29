package com.example.vladimir.financetracker.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet
import com.example.vladimir.financetracker.model.repository.Repository

class FinanceTrackerViewModel : ViewModel() {

    val observableWallets: MutableLiveData<MutableList<Wallet>> = MutableLiveData()
    val observableTransactions: MutableLiveData<MutableList<Transaction>> = MutableLiveData()
    val observableWallet: MutableLiveData<Wallet> = MutableLiveData()

    init {
        Repository.instance.initData()
    }

    fun getWallets(): MutableLiveData<MutableList<Wallet>>{
        observableWallets.value = Repository.instance.wallets
        return observableWallets
    }

    fun getSelectedWallet(): MutableLiveData<Wallet>{
        val data: MutableLiveData<Wallet> = MutableLiveData()
        data.value = Repository.instance.getSelectedWallet()
        return data
    }

    fun addWallet(wallet: Wallet){
        Repository.instance.addWallet(wallet)
    }

    fun getSelectedTransactions(): MutableLiveData<MutableList<Transaction>>{
        observableTransactions.value = Repository.instance.getSelectedTransactions()
        return observableTransactions
    }

    fun addTransaction(transaction: Transaction){
        Repository.instance.addTransaction(transaction)
    }
}