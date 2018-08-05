package com.example.vladimir.financetracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet
import com.example.vladimir.financetracker.model.repository.Repository

class FinanceTrackerViewModel : ViewModel() {

    val observableWallets: MutableLiveData<MutableList<Wallet>> = MutableLiveData()
    val observableTransactions: MutableLiveData<MutableList<Transaction>> = MutableLiveData()
    val observableWallet: MutableLiveData<Wallet?> = MutableLiveData()

    init {
        Repository.instance.initData()
        observableWallet.value = Repository.instance.wallets.firstOrNull()
        observableWallets.value = Repository.instance.wallets
        observableTransactions.value = Repository.instance.transactions
    }

    fun getUSD(): Double {
        return Repository.instance.USD
    }

    fun changeWallet(walletName: String) {
        Repository.instance.wallets.forEach {
            if (it.name == walletName) observableWallet.value = it
        }
        val list = Repository.instance.transactions.filter { it.wallet == walletName }
        observableTransactions.value = list.toMutableList()
    }

    fun addWallet(wallet: Wallet): Boolean {
        Repository.instance.wallets.forEach {
            if (it.name == wallet.name) return false
        }

        Repository.instance.db.walletDao().insert(wallet)
        observableWallets.value?.add(wallet)
        observableWallet.value = wallet

        Repository.instance.wallets.add(wallet)
        return true
    }

    fun addTransaction(transaction: Transaction) {
        observableWallet.value!!.balance += transaction.value

        Repository.instance.db.transactionDao().insert(transaction)
        if (transaction.wallet == observableWallet.value!!.name) {
            observableTransactions.value!!.add(transaction)
        }

        Repository.instance.transactions.add(transaction.copy(wallet = observableWallet.value!!.name))
    }
}
