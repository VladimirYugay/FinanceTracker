package com.example.vladimir.financetracker.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.extensions.R.id.info
import android.provider.Settings.Global.putString
import android.util.Log
import com.example.vladimir.financetracker.model.AppDatabase
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOError
import java.io.IOException
import java.net.URL

class Repository {

    val db: AppDatabase = AppDatabase.getDatabase()

    var USD = 60.0
    val wallets: MutableList<Wallet> = mutableListOf()
    val transactions: MutableList<Transaction> = mutableListOf()

    val walletsLd: LiveData<List<Wallet>> = db.walletDao().getAll()
    val transactionsLd: LiveData<List<Transaction>> = db.transactionDao().getAll()

    private object Holder {
        val INSTANCE = Repository()
    }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }

    init {
//        val wallet1 = Wallet("Кошелек", 250.0)
//        wallets.addAll(mutableListOf(wallet1))
        launch {
            getExchangeRate()
        }
    }

    fun initData() {
//        //SIMULATE DATA FROM DB
//
//        val wallet2 = Wallet("WALLET2", 550.0)
//        val wallet3 = Wallet("WALLET3", 750.0)
//        val wallet4 = Wallet("WALLET4", 150.0)
//
//        db.walletDao().insert(wallet2)
//        db.walletDao().insert(wallet3)
//        db.walletDao().insert(wallet4)
//
//
//        val transaction = Transaction("Burger", true, "RUB", "FOOD", 100.0, "1984", wallet2.name)
//        val transaction2 = Transaction("Sandwich", true, "USD", "FOOD", 80.0, "1984", wallet2.name)
//        val transaction3 = Transaction("Latte", true, "RUB", "FOOD", 150.0, "1984", wallet2.name)
//        val transaction4 = Transaction("Espresso", true, "RUB", "FOOD", 150.0, "1984", wallet3.name)
//        val transaction5 = Transaction("Tea", true, "RUB", "FOOD", 150.0, "1984", wallet4.name)
//        val transaction6 = Transaction("Naruto", true, "RUB", "FOOD", 100.0, "1984", wallet4.name)
//        transactions.addAll(mutableListOf(transaction, transaction2, transaction3, transaction4, transaction5, transaction6))
//
//        db.transactionDao().insert(transaction)
//        db.transactionDao().insert(transaction2)
//        db.transactionDao().insert(transaction3)
//        db.transactionDao().insert(transaction4)
//        db.transactionDao().insert(transaction5)
//        db.transactionDao().insert(transaction6)

    }

    fun getExchangeRate(){
        val client = OkHttpClient()
        val url = "http://free.currencyconverterapi.com/api/v6/convert?q=USD_RUB&compact=ultra"
        val request = Request.Builder().url(url).build()
        try {
            val response = client.newCall(request).execute()
            response.body()?.string()?.let {
                val data = JSONObject(it)
                USD = data.getDouble("USD_RUB")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}