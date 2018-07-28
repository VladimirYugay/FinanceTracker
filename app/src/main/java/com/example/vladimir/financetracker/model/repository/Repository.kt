package com.example.vladimir.financetracker.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

class Repository {

    init {

    }

    private object Holder {
        val INSTANCE = Repository()
    }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }

    fun getWallet(id: Int, name: String) : MutableLiveData<Wallet>{
        //get wallet from database
        val transaction = Transaction(0, "Burger", "BUY", "FOOD", 100.0)
        val transaction2 = Transaction(1, "Sandwich", "BUY", "FOOD", 80.0)
        val transaction3 = Transaction(2, "Latte", "BUY", "FOOD", 150.0)
        val data = MutableLiveData<Wallet>()
        val wallet = Wallet(0, "WALLET", 250.0, listOf(transaction, transaction2, transaction3))
        data.value = wallet
        return data
    }

}