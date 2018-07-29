package com.example.vladimir.financetracker.viewmodel

import android.arch.lifecycle.LiveData
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
        observableWallet.value = Repository.instance.wallets.first()
        observableWallets.value = Repository.instance.getAllWallets()
        observableTransactions.value = Repository.instance.getTransactions(Repository.instance.wallets.first().name)
    }

    fun getWallet(name: String): LiveData<Wallet>{
        val wallet = Repository.instance.getWallet(name)
        observableWallet.value = wallet
        return observableWallet
    }

    fun getAllWallets(): MutableLiveData<MutableList<Wallet>>{
        observableWallets.value = Repository.instance.getAllWallets()
        return observableWallets
    }

    fun changeWallet(walletName: String){
        observableTransactions.value = Repository.instance.getTransactions(walletName)
        Repository.instance.getAllWallets().forEach {
            if(it.name == walletName) observableWallet.value = it
        }
        observableWallets.value?.sortedWith(object : Comparator<Wallet>{
            override fun compare(p0: Wallet?, p1: Wallet?): Int {
                if(p0?.name.equals(walletName)) return -1
                else return 1
            }

        })
    }

    fun getTransactions(): MutableLiveData<MutableList<Transaction>>{
        observableTransactions.value = observableWallet.value?.name?.let { Repository.instance.getTransactions(it) }
        return observableTransactions
    }

    fun addWallet(wallet: Wallet){
        Repository.instance.addWallet(wallet)
    }

    fun addTransaction(transaction: Transaction){
        observableWallet.value?.name?.let { transaction.copy(wallet = it) }?.let { Repository.instance.addTransaction(it) }
    }
}