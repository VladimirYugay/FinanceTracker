package com.example.vladimir.financetracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet
import com.example.vladimir.financetracker.model.repository.Repository

class WalletViewModel : ViewModel() {

    private lateinit var wallet: MutableLiveData<Wallet>

    fun getWallet(id: Int, name: String) {
        wallet = Repository.instance.getWallet(id, name)
    }

    fun getObservableField(): LiveData<Wallet> {
        getWallet(0, "AXAX")
        return this.wallet
    }

}