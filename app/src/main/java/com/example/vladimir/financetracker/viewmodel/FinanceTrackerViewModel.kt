package com.example.vladimir.financetracker.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.repository.Repository

class FinanceTrackerViewModel : ViewModel() {

    val liveTransactions = Repository.instance.getLiveTransactions()

    fun addTransaction(transaction: Transaction){
        liveTransactions.value?.add(transaction)
    }
}