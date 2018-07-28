package com.example.vladimir.financetracker.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.repository.Repository

class FinanceTrackerViewModel : ViewModel() {

    var liveTransactions: MutableLiveData<MutableList<Transaction>> = Repository.instance.getLiveTransactions()

    fun addTransaction(transaction: Transaction) {
        Repository.instance.transactions.value?.add(transaction)
    }
}